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
@Table(name = "tb_item_pedido")
public class ItemPedido {
	
	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido", nullable = false)
    private Long idItemPedido;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido_usuario")
    private PedidoUsuario pedidoUsuario;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;
    
    //Construtores
    public ItemPedido() {
    	
    }

	public ItemPedido(Long idItemPedido, Integer quantidade, Produto produto, PedidoUsuario pedidoUsuario, BigDecimal subTotal) {
		super();
		this.idItemPedido = idItemPedido;
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedidoUsuario = pedidoUsuario;
		this.subTotal = subTotal;
	}

	//Getters e Setters
	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setId(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public PedidoUsuario getPedidoUsuario() {
		return pedidoUsuario;
	}

	public void setPedidoUsuario(PedidoUsuario pedidoUsuario) {
		this.pedidoUsuario = pedidoUsuario;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
}

