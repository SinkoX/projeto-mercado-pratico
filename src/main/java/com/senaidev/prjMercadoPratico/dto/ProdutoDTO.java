package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoDTO {

    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private Integer quantidade;
    private LocalDate dataValidade;
    private Long idSubcategoria;

    // Campo para receber/enviar imagem como base64
    private String imagemProdutoBase64;

    public ProdutoDTO() {}

    public Long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }
    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    public Long getIdSubcategoria() {
        return idSubcategoria;
    }
    public void setIdSubcategoria(Long idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public String getImagemProdutoBase64() {
        return imagemProdutoBase64;
    }
    public void setImagemProdutoBase64(String imagemProdutoBase64) {
        this.imagemProdutoBase64 = imagemProdutoBase64;
    }
}
