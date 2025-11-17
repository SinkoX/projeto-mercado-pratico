package com.senaidev.prjMercadoPratico.services;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Fornecedor;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.FornecedorRepository;
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

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // 🔹 Entity → DTO
    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO(produto);

        if (produto.getFornecedor() != null) {
            dto.setFornecedor(new ProdutoDTO.FornecedorDTO(produto.getFornecedor()));
        }
        return dto;
    }

    // 🔹 DTO → Entity (ALTERADO)
    public Produto toEntity(ProdutoDTO dto, Categoria categoria, Subcategoria subcategoria, Fornecedor fornecedor) {

        Produto produto = new Produto();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setCategoria(categoria);
        produto.setSubCategoria(subcategoria);
        produto.setFornecedor(fornecedor);
        produto.setDescricaoProduto(dto.getDescricaoProduto());

        // 🔹 Validação correta
        boolean temImgUrl = dto.getImgUrl() != null && !dto.getImgUrl().isBlank();
        boolean temImagemBase64 = dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isBlank();

        if (temImgUrl && temImagemBase64) {
            throw new IllegalArgumentException("Envie apenas imgUrl OU imagemBase64, não ambos.");
        }

        if (!temImgUrl && !temImagemBase64) {
            throw new IllegalArgumentException("É obrigatório enviar imgUrl OU imagemBase64.");
        }

        // 🔹 Caso envie URL
        if (temImgUrl) {
            produto.setImgUrl(dto.getImgUrl());
            produto.setImagemProduto(null);
        }

        // 🔹 Caso envie imagem Base64
        if (temImagemBase64) {
            String base64Image = dto.getImagemProdutoBase64().split(",")[1];
            produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
            produto.setImgUrl(null);
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

        Categoria categoria;
        Subcategoria subcategoria = null;
        Fornecedor fornecedor = null;

        // Categoria
        if (dto.getCategoria() != null && dto.getCategoria().getIdCategoria() != null) {
            categoria = categoriaRepository.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getCategoria().getIdCategoria()));
        } else {
            throw new RuntimeException("Categoria é obrigatória ao cadastrar um produto!");
        }

        // Subcategoria
        if (dto.getSubCategoria() != null && dto.getSubCategoria().getIdSubcategoria() != null) {
            subcategoria = subcategoriaRepository.findById(dto.getSubCategoria().getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada com ID: " + dto.getSubCategoria().getIdSubcategoria()));
        }

        // Fornecedor
        if (dto.getFornecedor() != null && dto.getFornecedor().getIdFornecedor() != null) {
            fornecedor = fornecedorRepository.findById(dto.getFornecedor().getIdFornecedor())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + dto.getFornecedor().getIdFornecedor()));
        }

        Produto produto = toEntity(dto, categoria, subcategoria, fornecedor);
        produto = produtoRepository.save(produto);

        return toDTO(produto);
    }

    // 🔹 Atualizar produto
    public ProdutoDTO updateDTO(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        // Categoria
        if (dto.getCategoria() != null && dto.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
            produto.setCategoria(categoria);
        }

        // Subcategoria
        if (dto.getSubCategoria() != null && dto.getSubCategoria().getIdSubcategoria() != null) {
            Subcategoria subcategoria = subcategoriaRepository.findById(dto.getSubCategoria().getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada."));
            produto.setSubCategoria(subcategoria);
        }

        // Fornecedor
        if (dto.getFornecedor() != null && dto.getFornecedor().getIdFornecedor() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedor().getIdFornecedor())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));
            produto.setFornecedor(fornecedor);
        }

        produto.setNomeProduto(dto.getNomeProduto());
        produto.setPrecoProduto(dto.getPrecoProduto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setDescricaoProduto(dto.getDescricaoProduto());

        // Atualização da imagem
        boolean temImgUrl = dto.getImgUrl() != null && !dto.getImgUrl().isBlank();
        boolean temImagemBase64 = dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isBlank();

        if (temImgUrl && temImagemBase64) {
            throw new IllegalArgumentException("Envie apenas imgUrl OU imagemBase64, não ambos.");
        }

        if (temImgUrl) {
            produto.setImgUrl(dto.getImgUrl());
            produto.setImagemProduto(null);
        }

        if (temImagemBase64) {
            String base64Image = dto.getImagemProdutoBase64().split(",")[1];
            produto.setImagemProduto(Base64.getDecoder().decode(base64Image));
            produto.setImgUrl(null);
        }

        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // 🔹 Deletar
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    // 🔹 Inserir direto
    public Produto insert(Produto produto) {
        return produtoRepository.save(produto);
    }
}
