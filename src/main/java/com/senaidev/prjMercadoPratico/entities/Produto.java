package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Produto", nullable = false)
    private Long idProduto;
    
    @Column(name = "nome_Produto", nullable = false, length = 45)
    private String nomeProduto;
    
    @Column(name = "preco_Produto", nullable = false)
    private Double precoProduto;
    
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    
    @Column(name = "categoria", nullable = false)
    private String categoria;
    
    @Column(name = "data_Validade", nullable = false)
    private LocalDate dataValidade;

    @Lob
    @Column(name = "imagem_Produto", columnDefinition = "LONGBLOB")
    private byte[] imagemProduto;

    // Construtor padrão
    public Produto() {
    }

   
    // ✅ Construtor com imagem (opcional)
    public Produto(Long idProduto, String nomeProduto, Double precoProduto, Integer quantidade, String categoria, LocalDate dataValidade, byte[] imagemProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.dataValidade = dataValidade;
        this.imagemProduto = imagemProduto;
    }

    // Getters e Setters
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

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public byte[] getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(byte[] imagemProduto) {
        this.imagemProduto = imagemProduto;
    }
}
