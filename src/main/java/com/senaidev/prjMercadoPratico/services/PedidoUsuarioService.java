package com.senaidev.prjMercadoPratico.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.Carrinho;
import com.senaidev.prjMercadoPratico.entities.Endereco;
import com.senaidev.prjMercadoPratico.entities.ItemPedido;
import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.enums.StatusPedido;
import com.senaidev.prjMercadoPratico.repositories.CarrinhoRepository;
import com.senaidev.prjMercadoPratico.repositories.PedidoUsuarioRepository;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PedidoUsuarioService {

    private final PedidoUsuarioRepository pedidoUsuarioRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoService carrinhoService;
    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    private static final String STRIPE_SECRET_KEY = "sk_test_51SOSMt1b6fknUuf8Rid6zxVO29KfcQEtzgBVM905EqEmJWWI3jLZZng6jwY29UHV1B9EbFWSQE2u1z9QziKYEw7T00PLL3FvU1";

    public PedidoUsuarioService(PedidoUsuarioRepository pedidoUsuarioRepository,
                                CarrinhoService carrinhoService,
                                CarrinhoRepository carrinhoRepository,
                                MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.pedidoUsuarioRepository = pedidoUsuarioRepository;
        this.carrinhoService = carrinhoService;
        this.carrinhoRepository = carrinhoRepository;
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    /** Cria pedido e retorna URL do Stripe Checkout */
    @Transactional
    public String criarPedidoComStripe(Long idUsuario, Endereco enderecoEntrega,
                                       BigDecimal frete, BigDecimal desconto) throws Exception {

        Carrinho carrinho = carrinhoService.buscarCarrinhoPorUsuario(idUsuario);

        if (carrinho == null || carrinho.getItensCarrinho().isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio ou não foi encontrado.");
        }

        List<ItemPedido> itensPedido = carrinho.getItensCarrinho().stream()
                .map(item -> new ItemPedido(null, item.getQuantidade(), item.getProduto(), null, item.getSubTotal()))
                .collect(Collectors.toList());

        PedidoUsuario pedido = new PedidoUsuario(
                carrinho.getUsuario(),
                itensPedido,
                frete,
                desconto,
                enderecoEntrega
        );
        pedido.setStatusPedido(StatusPedido.PENDENTE);

        PedidoUsuario pedidoSalvo = pedidoUsuarioRepository.save(pedido);

        return criarSessaoStripe(pedidoSalvo);
    }

    /** Cria sessão Stripe Checkout com frete e desconto */
    private String criarSessaoStripe(PedidoUsuario pedido) throws Exception {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        List<SessionCreateParams.LineItem> lineItems = pedido.getItensPedido().stream()
                .map(item -> SessionCreateParams.LineItem.builder()
                        .setQuantity(Long.valueOf(item.getQuantidade()))
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("brl")
                                        .setUnitAmount(item.getProduto().getPrecoProduto()
                                                .multiply(BigDecimal.valueOf(100))
                                                .longValue())
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getProduto().getNomeProduto())
                                                        .build())
                                        .build())
                        .build())
                .collect(Collectors.toList());

        // Frete
        if (pedido.getFrete() != null && pedido.getFrete().compareTo(BigDecimal.ZERO) > 0) {
            SessionCreateParams.LineItem freteItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(1L)
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("brl")
                                    .setUnitAmount(pedido.getFrete().multiply(BigDecimal.valueOf(100)).longValue())
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName("Frete")
                                                    .build())
                                    .build())
                    .build();

            lineItems.add(freteItem);
        }

        // Desconto
        if (pedido.getDesconto() != null && pedido.getDesconto().compareTo(BigDecimal.ZERO) > 0) {
            SessionCreateParams.LineItem descontoItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(1L)
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("brl")
                                    .setUnitAmount(pedido.getDesconto().multiply(BigDecimal.valueOf(-100)).longValue())
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName("Desconto")
                                                    .build())
                                    .build())
                    .build();

            lineItems.add(descontoItem);
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(lineItems)
                .setSuccessUrl("http://localhost:5173/sucesso?pedido=" + pedido.getIdPedidoUsuario())
                .setCancelUrl("http://localhost:5173/cancelado")
                .setClientReferenceId(String.valueOf(pedido.getIdPedidoUsuario()))
                .build();

        Session session = Session.create(params);

        // Salvar paymentIntent no pedido
        pedido.setPaymentIntent(session.getPaymentIntent());
        pedidoUsuarioRepository.save(pedido);

        return session.getUrl();
    }

    // =================== CRUD ===================

    public List<PedidoUsuario> listarTodos() {
        return pedidoUsuarioRepository.findAll();
    }

    public PedidoUsuario buscarPorId(Long id) {
        return pedidoUsuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + id));
    }

    @Transactional
    public PedidoUsuario atualizarStatus(Long idPedido, StatusPedido novoStatus) {
        PedidoUsuario pedido = buscarPorId(idPedido);
        pedido.setStatusPedido(novoStatus);
        return pedidoUsuarioRepository.save(pedido);
    }

    public void deletarPedido(Long idPedido) {
        PedidoUsuario pedido = buscarPorId(idPedido);
        pedidoUsuarioRepository.delete(pedido);
    }

    public List<PedidoUsuario> buscarPorUsuario(Long idUsuario) {
        return pedidoUsuarioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    /** Busca pedido pelo PaymentIntent do Stripe */
    public PedidoUsuario buscarPorPaymentIntent(String paymentIntentId) {
        return pedidoUsuarioRepository.findByPaymentIntent(paymentIntentId).orElse(null);
    }

    /** Salva pedido no banco */
    @Transactional
    public PedidoUsuario salvarPedido(PedidoUsuario pedido) {
        return pedidoUsuarioRepository.save(pedido);
    }

    /** Atualiza status para PAGO e limpa carrinho e registra saída no estoque */
    @Transactional
    public void marcarComoPagoELimparCarrinho(PedidoUsuario pedido) {
        pedido.setStatusPedido(StatusPedido.PAGO);
        pedidoUsuarioRepository.save(pedido);

        // Registra saída no estoque
        movimentacaoEstoqueService.registrarSaida(pedido);

        // Limpa carrinho
        Carrinho carrinho = pedido.getUsuario().getCarrinho();
        if (carrinho != null && carrinho.getItensCarrinho() != null) {
            carrinho.getItensCarrinho().clear();
            carrinhoRepository.save(carrinho);
        }
    }
}
