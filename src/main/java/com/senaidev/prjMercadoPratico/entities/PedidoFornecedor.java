package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido_fornecedor")
public class PedidoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pedido_Fornecedor")
    private Long idPedidoFornecedor;

    @Column(name = "data_Pedido_Fornecedor")
    private LocalDate dataPedidoFornecedor;

    @ManyToOne
    @JoinColumn(name = "id_Produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_Fornecedor")
    private Fornecedor fornecedor;

    // Construtor padrão
    public PedidoFornecedor() {
    }

    // Construtor com todos os campos
    public PedidoFornecedor(Long idPedidoFornecedor, LocalDate dataPedidoFornecedor, Produto produto, Fornecedor fornecedor) {
        this.idPedidoFornecedor = idPedidoFornecedor;
        this.dataPedidoFornecedor = dataPedidoFornecedor;
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

    public LocalDate getDataPedidoFornecedor() {
        return dataPedidoFornecedor;
    }

    public void setDataPedidoFornecedor(LocalDate dataPedidoFornecedor) {
        this.dataPedidoFornecedor = dataPedidoFornecedor;
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
