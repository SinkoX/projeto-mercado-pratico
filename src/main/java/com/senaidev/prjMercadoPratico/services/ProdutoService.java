package com.senaidev.prjMercadoPratico.services;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.repositories.SubcategoriaRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    // Converte Entity → DTO
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto);
    }

    // Converte DTO → Entity
    public Produto toEntity(ProdutoDTO dto, Subcategoria subcategoria) {
        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setSubcategoria(subcategoria);
        produto.setDescricao(dto.getDescricaoProduto()); // 🔹 Adiciona descrição

        // Mantém a URL da imagem, se fornecida
        if (dto.getImgUrl() != null && !dto.getImgUrl().isEmpty()) {
            produto.setImgUrl(dto.getImgUrl());
        }

        // Converte Base64 em bytes, se enviado
        if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isEmpty()) {
            String base64Image = dto.getImagemProdutoBase64().split(",")[1];
            produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
        }

        return produto;
    }

    // Buscar todos os produtos como DTO
    public List<ProdutoDTO> findAllDTO() {
        return produtoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar produto por ID como DTO
    public ProdutoDTO findByIdDTO(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return toDTO(produto);
    }

    // Inserir produto via DTO
    public ProdutoDTO insertDTO(ProdutoDTO dto) {
        Subcategoria subcategoria = subcategoriaRepository.findById(dto.getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada com ID: " + dto.getIdSubcategoria()));

        Produto produto = toEntity(dto, subcategoria);
        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // Atualizar produto via DTO
    public ProdutoDTO updateDTO(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        Subcategoria subcategoria = subcategoriaRepository.findById(dto.getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada com ID: " + dto.getIdSubcategoria()));

        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setSubcategoria(subcategoria);
        produto.setDescricao(dto.getDescricaoProduto()); // 🔹 Atualiza descrição

        // Atualiza Base64
        if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isEmpty()) {
            String base64Image = dto.getImagemProdutoBase64().split(",")[1];
            produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
        }

        // Atualiza imgUrl
        if (dto.getImgUrl() != null && !dto.getImgUrl().isEmpty()) {
            produto.setImgUrl(dto.getImgUrl());
        }

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // Deletar produto
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    // Salvar produto diretamente
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
}
