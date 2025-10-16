package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

import com.senaidev.prjMercadoPratico.entities.Produto;

public class ProdutoDTO {

    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private Integer quantidade;
    private LocalDate dataValidade;
    private Long idSubcategoria;

    private String imgUrl; // <--- novo campo para imagem externa
    private String imagemProdutoBase64; // <--- imagem enviada via upload

    public ProdutoDTO() {}

    // Construtor a partir da entidade
    public ProdutoDTO(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getNomeProduto();
        this.precoProduto = produto.getPrecoProduto();
        this.quantidade = produto.getQuantidade();
        this.dataValidade = produto.getDataValidade();
        this.idSubcategoria = produto.getSubcategoria() != null ? produto.getSubcategoria().getIdSubcategoria() : null;
        this.imgUrl = produto.getImgUrl();

        if (produto.getImagemProduto() != null) {
            this.imagemProdutoBase64 = Base64.getEncoder().encodeToString(produto.getImagemProduto());
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

    public Long getIdSubcategoria() { return idSubcategoria; }
    public void setIdSubcategoria(Long idSubcategoria) { this.idSubcategoria = idSubcategoria; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public String getImagemProdutoBase64() { return imagemProdutoBase64; }
    public void setImagemProdutoBase64(String imagemProdutoBase64) { this.imagemProdutoBase64 = imagemProdutoBase64; }
}
