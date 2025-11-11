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

    // 🔹 Criar estoque
    @Transactional
    public EstoqueDTO criar(Long idProduto, Integer quantidadeInicial, Integer quantidadeMinima) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + idProduto));

        if (estoqueRepository.findByProduto(produto).isPresent()) {
            throw new IllegalStateException("Já existe estoque para este produto.");
        }

        Estoque estoque = new Estoque(produto, quantidadeInicial, quantidadeMinima);
        return new EstoqueDTO(estoqueRepository.save(estoque));
    }

    // 🔹 Listar todos
    public List<EstoqueDTO> listarTodos() {
        return estoqueRepository.findAll().stream()
                .map(EstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar por ID
    public EstoqueDTO buscarPorId(Long id) {
        return new EstoqueDTO(estoqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + id)));
    }

    // 🔹 Buscar por Produto
    public EstoqueDTO buscarPorProduto(Long idProduto) {
        return new EstoqueDTO(estoqueRepository.findByProdutoIdProduto(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado para o produto ID: " + idProduto)));
    }

    // 🔹 Atualizar quantidade mínima
    @Transactional
    public EstoqueDTO atualizarQuantidadeMinima(Long idEstoque, Integer novaQuantidadeMinima) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));

        estoque.setQuantidadeMinima(novaQuantidadeMinima);
        return new EstoqueDTO(estoqueRepository.save(estoque));
    }

    // 🔹 Adicionar quantidade
    @Transactional
    public EstoqueDTO adicionarQuantidade(Long idEstoque, Integer quantidade) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));

        estoque.adicionarQuantidade(quantidade);
        return new EstoqueDTO(estoqueRepository.save(estoque));
    }

    // 🔹 Remover quantidade
    @Transactional
    public EstoqueDTO removerQuantidade(Long idEstoque, Integer quantidade) {
        Estoque estoque = estoqueRepository.findById(idEstoque)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque));

        estoque.removerQuantidade(quantidade);
        return new EstoqueDTO(estoqueRepository.save(estoque));
    }

    // 🔹 Buscar estoques abaixo do mínimo
    public List<EstoqueDTO> buscarEstoqueAbaixoDoMinimo() {
        return estoqueRepository.findEstoqueAbaixoDoMinimo().stream()
                .map(EstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar estoques zerados
    public List<EstoqueDTO> buscarEstoqueZerado() {
        return estoqueRepository.findEstoqueZerado().stream()
                .map(EstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Deletar
    @Transactional
    public void deletar(Long idEstoque) {
        if (!estoqueRepository.existsById(idEstoque)) {
            throw new IllegalArgumentException("Estoque não encontrado com ID: " + idEstoque);
        }
        estoqueRepository.deleteById(idEstoque);
    }

    // 🔹 Obter entidade (uso interno)
    public Estoque obterEstoquePorProduto(Long idProduto) {
        return estoqueRepository.findByProdutoIdProduto(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado para o produto ID: " + idProduto));
    }
    @Transactional
    public EstoqueDTO criar(EstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + dto.getIdProduto()));

        if (estoqueRepository.findByProduto(produto).isPresent()) {
            throw new IllegalStateException("Já existe estoque para este produto.");
        }

        Estoque estoque = new Estoque(produto, dto.getQuantidade(), dto.getQuantidadeMinima());
        return new EstoqueDTO(estoqueRepository.save(estoque));
    }

    
    
}
