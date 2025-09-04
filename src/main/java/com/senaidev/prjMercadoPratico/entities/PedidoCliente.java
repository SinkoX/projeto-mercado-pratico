package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido_cliente")
public class PedidoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_cliente")
    private Long idPedidoCliente;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "preco_total")
    private Double precoTotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_entrega")
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento")
    private FormaPagamento formaPagamento;

    @ManyToMany
    @JoinTable(
        name = "pedido_cliente_produto",
        joinColumns = @JoinColumn(name = "id_pedido_cliente"),
        inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private List<Produto> produtos;

    // Construtor padrão
    public PedidoCliente() {
    }

    // Construtor com todos os campos
    public PedidoCliente(Long idPedidoCliente, String status, LocalDate dataPedido, Double precoTotal, 
                         Cliente cliente, Entrega entrega, Funcionario funcionario, FormaPagamento formaPagamento, 
                         List<Produto> produtos) {
        this.idPedidoCliente = idPedidoCliente;
        this.status = status;
        this.dataPedido = dataPedido;
        this.precoTotal = precoTotal;
        this.cliente = cliente;
        this.entrega = entrega;
        this.funcionario = funcionario;
        this.formaPagamento = formaPagamento;
        this.produtos = produtos;
    }

    // Getters e Setters

    public Long getIdPedidoCliente() {
        return idPedidoCliente;
    }

    public void setIdPedidoCliente(Long idPedidoCliente) {
        this.idPedidoCliente = idPedidoCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
