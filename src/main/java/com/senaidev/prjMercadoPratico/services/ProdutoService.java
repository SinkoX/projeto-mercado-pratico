package com.senaidev.prjMercadoPratico.services;

import java.time.LocalDate;
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

    // Conversão Entity -> DTO
    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setIdProduto(produto.getIdProduto());
        dto.setNomeProduto(produto.getNomeProduto());
        dto.setPrecoProduto(produto.getPrecoProduto());
        dto.setQuantidade(produto.getQuantidade());
        dto.setDataValidade(produto.getDataValidade());
        if (produto.getSubcategoria() != null) {
            dto.setIdSubcategoria(produto.getSubcategoria().getIdSubcategoria());
        }
        return dto;
    }

    // Conversão DTO -> Entity
    public Produto toEntity(ProdutoDTO dto, Subcategoria subcategoria) {
        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setSubcategoria(subcategoria);
        return produto;
    }

    // Listar todos produtos DTO
    public List<ProdutoDTO> findAllDTO() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID DTO
    public ProdutoDTO findByIdDTO(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return toDTO(produto);
    }

    // Inserir Produto a partir de DTO
    public ProdutoDTO insertDTO(ProdutoDTO dto) {
        Subcategoria subcategoria = subcategoriaRepository.findById(dto.getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada com ID: " + dto.getIdSubcategoria()));

        Produto produto = toEntity(dto, subcategoria);
        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // Atualizar Produto a partir de DTO
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

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // Delete permanece igual, usando entity id
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    // Aqui você pode adaptar outros métodos findByNome, findByCategoria etc para usarem DTO se quiser
}
