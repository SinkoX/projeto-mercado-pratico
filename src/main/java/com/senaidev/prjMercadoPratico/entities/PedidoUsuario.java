package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido_usuario")
public class PedidoUsuario {
	
	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_usuario", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;

    @OneToMany(mappedBy = "pedidoUsuario", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
    	
    @Column(name = "status_pedido_usuario", nullable = false)
    private String status;
    
    @Column(name = "data_pedido_usuario", nullable = false)
    private LocalDate dataPedidoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_Funcionario")
    private Funcionario funcionario;

   	@ManyToOne
   	@JoinColumn(name = "id_Entrega")
   	private Entrega entrega;

   	@ManyToOne
   	@JoinColumn(name = "id_Forma_Pagamento")
   	private FormaPagamento formaPagamento;

   	//Construtores
   	public PedidoUsuario() {
   		
   	}

	public PedidoUsuario(Long id, Carrinho carrinho, List<ItemPedido> itensPedido, BigDecimal valorTotal, LocalDate dataPedidoUsuario, String status,
			Usuario usuario, Funcionario funcionario, Entrega entrega, FormaPagamento formaPagamento) {
		this.id = id;
		this.carrinho = carrinho;
		this.itensPedido = itensPedido;
		this.valorTotal = valorTotal;
		this.dataPedidoUsuario = dataPedidoUsuario;
		this.status = status;
		this.usuario = usuario;
		this.funcionario = funcionario;
		this.entrega = entrega;
		this.formaPagamento = formaPagamento;
	}

	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public LocalDate getDataPedidoUsuario() {
		return dataPedidoUsuario;
	}

	public void setDataPedidoUsuario(LocalDate dataPedidoUsuario) {
		this.dataPedidoUsuario = dataPedidoUsuario;
	}
}

