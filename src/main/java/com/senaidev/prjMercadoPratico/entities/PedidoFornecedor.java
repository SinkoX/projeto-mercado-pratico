package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido_fornecedor")
public class PedidoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_fornecedor")
    private Long idPedidoFornecedor;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    // Construtor padrão
    public PedidoFornecedor() {
    }

    // Construtor com todos os campos
    public PedidoFornecedor(Long idPedidoFornecedor, LocalDate dataPedido, Produto produto, Fornecedor fornecedor) {
        this.idPedidoFornecedor = idPedidoFornecedor;
        this.dataPedido = dataPedido;
        this.produto = produto;
        this.fornecedor = fornecedor;
    }

    // Getters & Setters
    public Long getIdPedidoFornecedor() {
        return idPedidoFornecedor;
    }

    public void setIdPedidoFornecedor(Long idPedidoFornecedor) {
        this.idPedidoFornecedor = idPedidoFornecedor;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
