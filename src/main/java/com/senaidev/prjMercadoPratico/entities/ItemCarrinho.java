package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_item_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_carrinho", nullable = false)
    private Long idItemCarrinho;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_carrinho", nullable = false)
    private Carrinho carrinho;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    // Construtores
    public ItemCarrinho() {}

    public ItemCarrinho(Long idItemCarrinho, Integer quantidade, BigDecimal precoUnitario,
                        Produto produto, Carrinho carrinho, BigDecimal subTotal) {
        this.idItemCarrinho = idItemCarrinho;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.produto = produto;
        this.carrinho = carrinho;
        this.subTotal = subTotal;
    }

    // Getters e Setters
    public Long getIdItemCarrinho() {
        return idItemCarrinho;
    }

    public void setIdItemCarrinho(Long idItemCarrinho) {
        this.idItemCarrinho = idItemCarrinho;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        atualizarSubtotal();
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        atualizarSubtotal();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    // Atualiza subtotal automaticamente
    public void atualizarSubtotal() {
        if (precoUnitario != null && quantidade != null) {
            this.subTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}
