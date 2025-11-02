package com.senaidev.prjMercadoPratico.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.Carrinho;
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

    private static final String STRIPE_SECRET_KEY = "sk_test_51SOSMt1b6fknUuf8Rid6zxVO29KfcQEtzgBVM905EqEmJWWI3jLZZng6jwY29UHV1B9EbFWSQE2u1z9QziKYEw7T00PLL3FvU1";

    public PedidoUsuarioService(PedidoUsuarioRepository pedidoUsuarioRepository,
                                CarrinhoService carrinhoService,
                                CarrinhoRepository carrinhoRepository) {
        this.pedidoUsuarioRepository = pedidoUsuarioRepository;
        this.carrinhoService = carrinhoService;
        this.carrinhoRepository = carrinhoRepository;
    }

    /** Cria pedido e retorna URL do Stripe Checkout */
    @Transactional
    public String criarPedidoComStripe(Long idUsuario) throws Exception {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorUsuario(idUsuario);

        if (carrinho == null || carrinho.getItensCarrinho().isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio ou não foi encontrado.");
        }

        List<ItemPedido> itensPedido = carrinho.getItensCarrinho().stream()
                .map(itemCarrinho -> new ItemPedido(
                        null,
                        itemCarrinho.getQuantidade(),
                        itemCarrinho.getProduto(),
                        null,
                        itemCarrinho.getSubTotal()
                ))
                .collect(Collectors.toList());

        PedidoUsuario pedido = new PedidoUsuario(carrinho.getUsuario(), itensPedido);
        pedido.setStatusPedido(StatusPedido.PENDENTE);

        PedidoUsuario pedidoSalvo = pedidoUsuarioRepository.save(pedido);

        return criarSessaoStripe(pedidoSalvo);
    }

    /** Cria sessão Stripe Checkout */
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

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(lineItems)
                .setSuccessUrl("http://localhost:5173/sucesso?pedido=" + pedido.getIdPedidoUsuario())
                .setCancelUrl("http://localhost:5173/cancelado")
                .setClientReferenceId(String.valueOf(pedido.getIdPedidoUsuario()))
                .build();

        Session session = Session.create(params);
        return session.getUrl();
    }

    // 🔹 CRUD básico
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
        pedido.atualizarStatus(novoStatus);
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

    /** Atualiza status para PAGO e limpa carrinho */
    @Transactional
    public void marcarComoPagoELimparCarrinho(PedidoUsuario pedido) {
        pedido.atualizarStatus(StatusPedido.PAGO);
        pedidoUsuarioRepository.save(pedido);

        Carrinho carrinho = pedido.getUsuario().getCarrinho();
        if (carrinho != null && carrinho.getItensCarrinho() != null) {
            carrinho.getItensCarrinho().clear();
            carrinhoRepository.save(carrinho);
        }
    }
}
