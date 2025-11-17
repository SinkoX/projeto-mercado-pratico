package com.senaidev.prjMercadoPratico.services;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.repositories.SubcategoriaRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository; // ✅ Adicionado

    // 🔹 Entity → DTO
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto);
    }

    // 🔹 DTO → Entity
    public Produto toEntity(ProdutoDTO dto, Categoria categoria, Subcategoria subcategoria) {
        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setCategoria(categoria);
        produto.setSubCategoria(subcategoria); 
        produto.setDescricaoProduto(dto.getDescricaoProduto());

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
        Categoria categoria = null;
        Subcategoria subcategoria = null;

        // 🔸 Buscar categoria
        if (dto.getCategoria() != null && dto.getCategoria().getIdCategoria() != null) {
            categoria = categoriaRepository.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getCategoria().getIdCategoria()));
        } else {
            throw new RuntimeException("Categoria é obrigatória ao cadastrar um produto!");
        }

        // 🔸 Buscar subcategoria
        if (dto.getSubCategoria() != null && dto.getSubCategoria().getIdSubcategoria() != null) {
            subcategoria = subcategoriaRepository.findById(dto.getSubCategoria().getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada com ID: " + dto.getSubCategoria().getIdSubcategoria()));
        }

        Produto produto = toEntity(dto, categoria, subcategoria);
        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // 🔹 Atualizar produto
    public ProdutoDTO updateDTO(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        if (dto.getCategoria() != null && dto.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getCategoria().getIdCategoria()));
            produto.setCategoria(categoria);
        }

        if (dto.getSubCategoria() != null && dto.getSubCategoria().getIdSubcategoria() != null) {
            Subcategoria subcategoria = subcategoriaRepository.findById(dto.getSubCategoria().getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada com ID: " + dto.getSubCategoria().getIdSubcategoria()));
            produto.setSubCategoria(subcategoria);
        }

        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setDescricaoProduto(dto.getDescricaoProduto());

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
