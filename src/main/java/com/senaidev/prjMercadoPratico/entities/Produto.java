package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private Long idProduto;
    
    @Column(name = "nome_produto", nullable = false, length = 100)
    private String nomeProduto;
    
    @Column(name = "preco_produto", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoProduto;
    
    @Column(name = "quantidade", nullable = true)
    private Integer quantidade;
    
    @Column(name = "data_validade", nullable = true)
    private LocalDate dataValidade;
    
    @Column(name = "descricao_produto", length = 1000)
    private String descricaoProduto;
    
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    
    @Lob
    @Column(name = "imagem_produto", columnDefinition = "LONGBLOB")
    private byte[] imagemProduto;
    
    @ManyToOne
    @JoinColumn(name = "id_categoria") 
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private Subcategoria subcategoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemPedido> itensPedido = new ArrayList<>();

    // Construtores
    public Produto() {
    }

    public Produto(Long idProduto, String nomeProduto, BigDecimal precoProduto, Integer quantidade,String descricaoProduto,
                   LocalDate dataValidade, String imgUrl, byte[] imagemProduto, Categoria categoria, Subcategoria subcategoria,  
                   List<ItemCarrinho> itensCarrinho, List<ItemPedido> itensPedido) {
        super();
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.imgUrl = imgUrl;
        this.imagemProduto = imagemProduto;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.itensCarrinho = itensCarrinho;
        this.itensPedido = itensPedido;
        this.descricaoProduto = descricaoProduto;
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
    
    public String getImgUrl() {
    	return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
    	this.imgUrl = imgUrl;
    }

    public byte[] getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(byte[] imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Subcategoria getSubCategoria() {
        return subcategoria;
    }

    public void setSubCategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public String getDescricaoProduto() {
    	return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
    	this.descricaoProduto = descricaoProduto;
    }
}