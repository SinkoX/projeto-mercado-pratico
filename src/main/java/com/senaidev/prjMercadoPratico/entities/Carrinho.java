package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Long idCarrinho;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemCarrinho> itens = new ArrayList<>();

    @OneToOne(mappedBy = "carrinho")
    private PedidoUsuario pedidoUsuario;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
    
    //Construtores
    public Carrinho() {
    	
    }

	public Carrinho(Long idCarrinho, List<ItemCarrinho> itens, PedidoUsuario pedidoUsuario, BigDecimal valorTotal) {
		super();
		this.idCarrinho = idCarrinho;
		this.itens = itens;
		this.pedidoUsuario = pedidoUsuario;
		this.valorTotal = valorTotal;
	}

	//Getters e Setters
	public Long getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(Long idCarrinho) {
		this.idCarrinho = idCarrinho;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public PedidoUsuario getPedidoUsuario() {
		return pedidoUsuario;
	}

	public void setPedidoUsuario(PedidoUsuario pedidoUsuario) {
		this.pedidoUsuario = pedidoUsuario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}

