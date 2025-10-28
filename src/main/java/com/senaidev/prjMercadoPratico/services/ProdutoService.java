package com.senaidev.prjMercadoPratico.services;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 🔹 Entity → DTO
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto);
    }

    // 🔹 DTO → Entity
    public Produto toEntity(ProdutoDTO dto, Categoria categoria) {
        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setCategoria(categoria);
        produto.setDescricao(dto.getDescricaoProduto());

        if (dto.getImgUrl() != null && !dto.getImgUrl().isEmpty()) {
            produto.setImgUrl(dto.getImgUrl());
        }

        if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isEmpty()) {
            String base64Image = dto.getImagemProdutoBase64().split(",")[1];
            produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
        }

        return produto;
    }

    // 🔹 Buscar todos como DTO
    public List<ProdutoDTO> findAllDTO() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar por ID
    public ProdutoDTO findByIdDTO(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return toDTO(produto);
    }

    // 🔹 Inserir novo produto
    public ProdutoDTO insertDTO(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getIdCategoria()));

        Produto produto = toEntity(dto, categoria);
        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // 🔹 Atualizar produto existente
    public ProdutoDTO updateDTO(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getIdCategoria()));

        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setCategoria(categoria);
        produto.setDescricao(dto.getDescricaoProduto());

        if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isEmpty()) {
            String base64Image = dto.getImagemProdutoBase64().split(",")[1];
            produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
        }

        if (dto.getImgUrl() != null && !dto.getImgUrl().isEmpty()) {
            produto.setImgUrl(dto.getImgUrl());
        }

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // 🔹 Deletar
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    // 🔹 Inserir direto (sem DTO)
    public Produto insert(Produto produto) {
        return produtoRepository.save(produto);
    }
}
