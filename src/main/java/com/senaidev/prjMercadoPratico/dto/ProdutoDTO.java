package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Fornecedor;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;

public class ProdutoDTO {

    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private LocalDate dataValidade;
    private String descricaoProduto;
    private Integer quantidade;
    private String imgUrl;
    private String imagemProdutoBase64;

    private CategoriaDTO categoria;
    private SubcategoriaDTO subCategoria;
    private FornecedorDTO fornecedor;

    public ProdutoDTO() {}

    public ProdutoDTO(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getNomeProduto();
        this.precoProduto = produto.getPrecoProduto();
        this.dataValidade = produto.getDataValidade();
        this.quantidade = produto.getQuantidade();
        this.descricaoProduto = produto.getDescricaoProduto();
        this.imgUrl = produto.getImgUrl();

        if (produto.getImagemProduto() != null) {
            this.imagemProdutoBase64 = "data:image/png;base64," +
                    Base64.getEncoder().encodeToString(produto.getImagemProduto());
        }

        if (produto.getCategoria() != null) {
            this.categoria = new CategoriaDTO(produto.getCategoria());
        }

        if (produto.getSubCategoria() != null) {
            this.subCategoria = new SubcategoriaDTO(produto.getSubCategoria());
        }
        
        if (produto.getFornecedor() != null) {
            this.fornecedor = new FornecedorDTO(produto.getFornecedor());
        }
    }

    // Getters e Setters
    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public BigDecimal getPrecoProduto() { return precoProduto; }
    public void setPrecoProduto(BigDecimal precoProduto) { this.precoProduto = precoProduto; }
    
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public String getDescricaoProduto() { return descricaoProduto; }
    public void setDescricaoProduto(String descricaoProduto) { this.descricaoProduto = descricaoProduto; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public String getImagemProdutoBase64() { return imagemProdutoBase64; }
    public void setImagemProdutoBase64(String imagemProdutoBase64) { this.imagemProdutoBase64 = imagemProdutoBase64; }

    public CategoriaDTO getCategoria() { return categoria; }
    public void setCategoria(CategoriaDTO categoria) { this.categoria = categoria; }

    public SubcategoriaDTO getSubCategoria() { return subCategoria; }
    public void setSubCategoria(SubcategoriaDTO subCategoria) { this.subCategoria = subCategoria; }
    
    public FornecedorDTO getFornecedor() { return fornecedor; }
    public void setFornecedor(FornecedorDTO fornecedor) { this.fornecedor = fornecedor; }

    // DTOs internos
    public static class CategoriaDTO {
        private Long idCategoria;
        private String nomeCategoria;

        public CategoriaDTO() {}
        
        public CategoriaDTO(Categoria categoria) {
            this.idCategoria = categoria.getIdCategoria();
            this.nomeCategoria = categoria.getNomeCategoria();
        }

        public Long getIdCategoria() { return idCategoria; }
        public String getNomeCategoria() { return nomeCategoria; }
    }

    public static class SubcategoriaDTO {
        private Long idSubcategoria;
        private String nomeSubcategoria;

        public SubcategoriaDTO() {}
        
        public SubcategoriaDTO(Subcategoria subcategoria) {
            this.idSubcategoria = subcategoria.getIdSubcategoria();
            this.nomeSubcategoria = subcategoria.getNomeSubcategoria();
        }

        public Long getIdSubcategoria() { return idSubcategoria; }
        public String getNomeSubcategoria() { return nomeSubcategoria; }
    }
    
    public static class FornecedorDTO {
        private Long idFornecedor;
        private String nomeFornecedor;
        private String emailFornecedor;
        private String telefoneFornecedor;

        public FornecedorDTO() {}

        public FornecedorDTO(Fornecedor fornecedor) {
            this.idFornecedor = fornecedor.getIdFornecedor();
            this.nomeFornecedor = fornecedor.getNomeFornecedor();
            this.emailFornecedor = fornecedor.getEmailFornecedor();
            this.telefoneFornecedor = fornecedor.getTelefoneFornecedor();
        }

        public Long getIdFornecedor() { return idFornecedor; }
        public String getNomeFornecedor() { return nomeFornecedor; }
        public String getEmailFornecedor() { return emailFornecedor; }
        public String getTelefoneFornecedor() { return telefoneFornecedor; }
    }
}
