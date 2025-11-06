package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.dto.EstoqueDTO;
import com.senaidev.prjMercadoPratico.entities.Estoque;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.EstoqueRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    // 🔹 Listar todos os estoques
    public List<EstoqueDTO> listarTodos() {
        return estoqueRepository.findAll().stream()
                .map(EstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar estoque por ID
    public EstoqueDTO buscarPorId(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + id));
        return new EstoqueDTO(estoque);
    }

    // 🔹 Buscar estoque por produto
    public EstoqueDTO buscarPorProduto(Long idProduto) {
        Estoque estoque = estoqueRepository.findByProdutoIdProduto(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado para o produto ID: " + idProduto));
        return new EstoqueDTO(estoque);
    }

    // 🔹 Criar estoque para um produto
    @Transactional
    public EstoqueDTO criar(Long idProduto, Integer quantidadeInicial, Integer quantidadeMinima) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + idProduto));

        // Verifica se já existe estoque para este produto
        if (estoqueRepository.findByProduto(produto).isPresent()) {
            throw new IllegalStateException("Já existe estoque para este produto");
        }

        Estoque estoque = new Estoque(produto, quantidadeInicial, quantidadeMinima);
        estoque = estoqueRepository.save(estoque);
        return new EstoqueDTO(estoque);
    }

    // 🔹 Atualizar quantidade mínima
    @Transactional
    public EstoqueDTO atualizarQuantidadeMinima(Long idEstoque, Integer novaQuantidadeMinima) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));

        estoque.setQuantidadeMinima(novaQuantidadeMinima);
        estoque = estoqueRepository.save(estoque);
        return new EstoqueDTO(estoque);
    }

    // 🔹 Adicionar quantidade manualmente
    @Transactional
    public EstoqueDTO adicionarQuantidade(Long idEstoque, Integer quantidade) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));

        estoque.adicionarQuantidade(quantidade);
        estoque = estoqueRepository.save(estoque);
        return new EstoqueDTO(estoque);
    }

    // 🔹 Remover quantidade manualmente
    @Transactional
    public EstoqueDTO removerQuantidade(Long idEstoque, Integer quantidade) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));

        estoque.removerQuantidade(quantidade);
        estoque = estoqueRepository.save(estoque);
        return new EstoqueDTO(estoque);
    }

    // 🔹 Buscar produtos com estoque abaixo do mínimo
    public List<EstoqueDTO> buscarEstoqueAbaixoDoMinimo() {
        return estoqueRepository.findEstoqueAbaixoDoMinimo().stream()
                .map(EstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar produtos sem estoque
    public List<EstoqueDTO> buscarEstoqueZerado() {
        return estoqueRepository.findEstoqueZerado().stream()
                .map(EstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Deletar estoque
    @Transactional
    public void deletar(Long idEstoque) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));
        estoqueRepository.delete(estoque);
    }

    // 🔹 Método auxiliar para obter entidade (usado internamente)
    @Transactional
    public Estoque obterEstoquePorProduto(Long idProduto) {
        return estoqueRepository.findByProdutoIdProduto(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado para o produto ID: " + idProduto));
    }
}