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
    private Long idCategoria;
    private String descricaoProduto;  // Nova variável para descrição
    private String imgUrl; // 🔹 Mantém URL da imagem
    private String imagemProdutoBase64; // 🔹 Imagem em Base64 (upload opcional)

    public ProdutoDTO() {}

    // 🔹 Construtor que converte Entity → DTO
    public ProdutoDTO(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getNomeProduto();
        this.precoProduto = produto.getPrecoProduto();
        this.quantidade = produto.getQuantidade();
        this.dataValidade = produto.getDataValidade();
        this.idCategoria = produto.getCategoria() != null ? produto.getCategoria().getIdCategoria() : null;

        this.descricaoProduto = produto.getDescricaoProduto();  // Copia a descrição para o DTO

        this.imgUrl = produto.getImgUrl(); // Mantém se existir

        // Converte imagem armazenada em bytes para Base64, se existir
        if (produto.getImagemProduto() != null) {
            this.imagemProdutoBase64 = "data:image/png;base64," +
                    Base64.getEncoder().encodeToString(produto.getImagemProduto());
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
    
    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    public String getDescricaoProduto() { return descricaoProduto; }
    public void setDescricaoProduto(String descricaoProduto) { this.descricaoProduto = descricaoProduto; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public String getImagemProdutoBase64() { return imagemProdutoBase64; }
    public void setImagemProdutoBase64(String imagemProdutoBase64) { this.imagemProdutoBase64 = imagemProdutoBase64; }
}
