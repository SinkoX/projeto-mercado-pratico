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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

   	@ManyToOne
   	@JoinColumn(name = "entrega_id")
   	private Entrega entrega;

   	@ManyToOne
   	@JoinColumn(name = "forma_pagamento_id")
   	private FormaPagamento formaPagamento;
}

