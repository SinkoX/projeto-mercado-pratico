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
    private SubcategoriaRepository subcategoriaRepository;

    // -------------------- Entity → DTO --------------------
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto);
    }

    // -------------------- DTO → Entity --------------------
    public Produto toEntity(ProdutoDTO dto, Categoria categoria, Subcategoria subcategoria) {
        Produto produto = new Produto();

        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setCategoria(categoria);
        produto.setSubCategoria(subcategoria);
        produto.setDescricaoProduto(dto.getDescricaoProduto());

        // ----- (1) Tratar URL opcional -----
        if (dto.getImgUrl() != null && !dto.getImgUrl().isBlank()) {
            produto.setImgUrl(dto.getImgUrl());
        }

        // ----- (2) Tratar Base64 opcional -----
        if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isBlank()) {
            try {
                String base64Image = dto.getImagemProdutoBase64().split(",")[1];
                produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
            } catch (Exception e) {
                System.out.println("Erro ao converter Base64 da imagem!");
            }
        }

        return produto;
    }

    // -------------------- Listar --------------------
    public List<ProdutoDTO> findAllDTO() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // -------------------- Buscar por Id --------------------
    public ProdutoDTO findByIdDTO(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        return toDTO(produto);
    }

    // -------------------- Inserir Produto --------------------
    public ProdutoDTO insertDTO(ProdutoDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        Subcategoria subcategoria = null;
        if (dto.getSubCategoria() != null && dto.getSubCategoria().getIdSubcategoria() != null) {
            subcategoria = subcategoriaRepository.findById(dto.getSubCategoria().getIdSubcategoria())
                    .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada!"));
        }

        Produto produto = toEntity(dto, categoria, subcategoria);
        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // -------------------- Atualizar Produto --------------------
    public ProdutoDTO updateDTO(Long id, ProdutoDTO dto) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        // Categoria
        if (dto.getCategoria() != null && dto.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria inválida!"));
            produto.setCategoria(categoria);
        }

        // Subcategoria
        if (dto.getSubCategoria() != null && dto.getSubCategoria().getIdSubcategoria() != null) {
            Subcategoria subcategoria = subcategoriaRepository.findById(dto.getSubCategoria().getIdSubcategoria())
                    .orElseThrow(() -> new RuntimeException("Subcategoria inválida!"));
            produto.setSubCategoria(subcategoria);
        }

        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setDescricaoProduto(dto.getDescricaoProduto());

        // ----- Atualizar imagem Base64 SE vier preenchida -----
        if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isBlank()) {
            try {
                String base64Image = dto.getImagemProdutoBase64().split(",")[1];
                produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
            } catch (Exception e) {
                System.out.println("Erro ao atualizar imagem Base64");
            }
        }

        // ----- Atualizar URL SE vier preenchida -----
        if (dto.getImgUrl() != null && !dto.getImgUrl().isBlank()) {
            produto.setImgUrl(dto.getImgUrl());
        }

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // -------------------- Deletar --------------------
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
