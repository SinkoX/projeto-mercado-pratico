package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.Carrinho;
import com.senaidev.prjMercadoPratico.entities.ItemPedido;
import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.enums.StatusPedido;
import com.senaidev.prjMercadoPratico.repositories.CarrinhoRepository;
import com.senaidev.prjMercadoPratico.repositories.PedidoUsuarioRepository;

@Service
public class PedidoUsuarioService {

    private final PedidoUsuarioRepository pedidoUsuarioRepository;
    private final CarrinhoService carrinhoService;

    public PedidoUsuarioService(PedidoUsuarioRepository pedidoUsuarioRepository,
                                CarrinhoRepository carrinhoRepository,
                                CarrinhoService carrinhoService) {
        this.pedidoUsuarioRepository = pedidoUsuarioRepository;
        this.carrinhoService = carrinhoService;
    }

    @Transactional
    public PedidoUsuario criarPedido(Long idUsuario) {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorUsuario(idUsuario);

        if (carrinho.getItensCarrinho().isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio");
        }

        Usuario usuario = carrinho.getUsuario();

        // Converte os itens do carrinho para itens de pedido
        List<ItemPedido> itensPedido = carrinho.getItensCarrinho().stream()
                .map(itemCarrinho -> new ItemPedido(
                        null,
                        itemCarrinho.getQuantidade(),
                        itemCarrinho.getProduto(),
                        null,
                        itemCarrinho.getSubTotal()
                ))
                .toList();

        PedidoUsuario pedido = new PedidoUsuario(usuario, itensPedido);
        PedidoUsuario pedidoSalvo = pedidoUsuarioRepository.save(pedido);

        // Limpa o carrinho após criação do pedido
        carrinhoService.limparCarrinho(idUsuario);

        return pedidoSalvo;
    }

    public List<PedidoUsuario> listarTodos() {
        return pedidoUsuarioRepository.findAll();
    }

    public PedidoUsuario buscarPorId(Long id) {
        return pedidoUsuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + id));
    }
    
    // busca pedidos por usuário
    public List<PedidoUsuario> buscarPorUsuario(Long idUsuario) {
        return pedidoUsuarioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    @Transactional
    public PedidoUsuario atualizarStatus(Long idPedido, StatusPedido novoStatus) {
        PedidoUsuario pedido = buscarPorId(idPedido);
        pedido.atualizarStatus(novoStatus);
        return pedidoUsuarioRepository.save(pedido);
    }
}
