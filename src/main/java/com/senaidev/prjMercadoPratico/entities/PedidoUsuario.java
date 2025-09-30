package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido_usuario")
public class PedidoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pedido_Usuario")
    private Long idPedidoUsuario;

    @Column(name = "status_Pedido_Usuario", nullable = false, length = 50)
    private String statusPedidoUsuario;

    @Column(name = "data_Pedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "preco_Total", nullable = false)
    private Double precoTotal;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_Entrega")
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "id_Funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_Forma_Pagamento")
    private FormaPagamento formaPagamento;

    @ManyToMany
    @JoinTable(
        name = "pedido_usuario_produto",
        joinColumns = @JoinColumn(name = "id_Pedido_Usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_Produto")
    )
    
    
    
    private List<Produto> produtos;

    // Construtor padrão
    public PedidoUsuario() {
    }

    // Construtor com todos os campos
    public PedidoUsuario(Long idPedidoUsuario, String statusPedidoUsuario, LocalDate dataPedido, Double precoTotal,
    		Usuario usuario, Entrega entrega, Funcionario funcionario, FormaPagamento formaPagamento, 
                         List<Produto> produtos) {
        this.idPedidoUsuario = idPedidoUsuario;
        this.statusPedidoUsuario = statusPedidoUsuario;
        this.dataPedido = dataPedido;
        this.precoTotal = precoTotal;
        this.usuario = usuario;
        this.entrega = entrega;
        this.funcionario = funcionario;
        this.formaPagamento = formaPagamento;
        this.produtos = produtos;
    }

    // Getters e Setters

    public Long getIdPedidoUsuario() {
        return idPedidoUsuario;
    }

    public void setIdPedidoUsuario(Long idPedidoUsuario) {
        this.idPedidoUsuario = idPedidoUsuario;
    }

    public String getStatusPedidoUsuario() {
        return statusPedidoUsuario;
    }

    public void setStatusPedidoUsuario(String statusPedidoUsuario) {
        this.statusPedidoUsuario = statusPedidoUsuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
