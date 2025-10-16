package com.senaidev.prjMercadoPratico.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.Carrinho;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.CarrinhoRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           ProdutoRepository produtoRepository,
                           UsuarioRepository usuarioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Carrinho buscarCarrinhoPorUsuario(Long idUsuario) {
        return carrinhoRepository.buscarCarrinhoPorUsuario(idUsuario)
                .orElseGet(() -> criarCarrinhoParaUsuario(idUsuario));
    }

    @Transactional
    public Carrinho criarCarrinhoParaUsuario(Long idUsuario) {
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado")));
        carrinho.setValorTotal(BigDecimal.ZERO);
        carrinho.setQuantidadeTotal(0);
        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho adicionarItem(Long idUsuario, Long idProduto, Integer quantidade) {
        Carrinho carrinho = buscarCarrinhoPorUsuario(idUsuario);
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        carrinho.adicionarItem(produto, quantidade);
        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho removerItem(Long idUsuario, Long idProduto) {
        Carrinho carrinho = buscarCarrinhoPorUsuario(idUsuario);
        carrinho.removerItem(idProduto);
        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho limparCarrinho(Long idUsuario) {
        Carrinho carrinho = buscarCarrinhoPorUsuario(idUsuario);
        carrinho.getItensCarrinho().clear();
        carrinho.atualizarTotais();
        return carrinhoRepository.save(carrinho);
    }
}

