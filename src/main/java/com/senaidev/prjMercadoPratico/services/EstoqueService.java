package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.Estoque;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;
import com.senaidev.prjMercadoPratico.repositories.EstoqueRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository,
                          ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorId(Long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado: " + id));
    }

    public Estoque buscarPorProduto(Long idProduto) {
        return estoqueRepository.findByProdutoIdProduto(idProduto);
    }

    public Estoque criarEstoque(Long idProduto, Integer quantidadeInicial) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido"));

        Estoque estoque = new Estoque(null, produto, quantidadeInicial, TipoMovimentacao.ENTRADA);
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque registrarEntrada(Long idProduto, Integer quantidade) {
        Estoque estoque = buscarPorProduto(idProduto);
        estoque.registrarEntrada(quantidade);
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque registrarSaida(Long idProduto, Integer quantidade) {
        Estoque estoque = buscarPorProduto(idProduto);
        estoque.registrarSaida(quantidade);
        return estoqueRepository.save(estoque);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        estoqueRepository.deleteById(id);
    }
}
