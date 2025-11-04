package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_pedido_fornecedor")
public class ItemPedidoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido_fornecedor", nullable = false)
    private Long idItemPedidoFornecedor;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido_fornecedor")
    private PedidoFornecedor pedidoFornecedor;

    // Construtor vazio
    protected ItemPedidoFornecedor() {}

    // Construtor completo
    public ItemPedidoFornecedor(Long idItemPedidoFornecedor, Integer quantidade,
                                BigDecimal subTotal, Produto produto,
                                PedidoFornecedor pedidoFornecedor) {
        this.idItemPedidoFornecedor = idItemPedidoFornecedor;
        this.quantidade = quantidade;
        this.subTotal = subTotal;
        this.produto = produto;
        this.pedidoFornecedor = pedidoFornecedor;
    }

    // Getters e Setters
    public Long getIdItemPedidoFornecedor() { return idItemPedidoFornecedor; }
    public void setIdItemPedidoFornecedor(Long idItemPedidoFornecedor) { this.idItemPedidoFornecedor = idItemPedidoFornecedor; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public PedidoFornecedor getPedidoFornecedor() { return pedidoFornecedor; }
    public void setPedidoFornecedor(PedidoFornecedor pedidoFornecedor) { this.pedidoFornecedor = pedidoFornecedor; }
}
