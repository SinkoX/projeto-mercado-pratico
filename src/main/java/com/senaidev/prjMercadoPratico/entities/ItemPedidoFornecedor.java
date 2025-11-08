package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_pedido_fornecedor")
public class ItemPedidoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido_fornecedor", nullable = false)
    private Long idItemPedidoFornecedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pedido_fornecedor", nullable = false)
    @JsonIgnore
    private PedidoFornecedor pedidoFornecedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "sub_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    protected ItemPedidoFornecedor() {}

    public ItemPedidoFornecedor(PedidoFornecedor pedidoFornecedor, Produto produto, 
                                Integer quantidade, BigDecimal precoUnitario) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo");
        if (quantidade == null || quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida");
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("Preço unitário inválido");

        this.pedidoFornecedor = pedidoFornecedor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    // Getters e Setters
    public Long getIdItemPedidoFornecedor() { return idItemPedidoFornecedor; }
    public PedidoFornecedor getPedidoFornecedor() { return pedidoFornecedor; }
    public Produto getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public BigDecimal getSubTotal() { return subTotal; }

    public void setPedidoFornecedor(PedidoFornecedor pedidoFornecedor) { this.pedidoFornecedor = pedidoFornecedor; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
}