package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_forma_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Forma_Pagamento")
    private Long idFormaPagamento;

    @Column(name = "forma_Pagamento", nullable = false)
    private String formaPagamento; // Ex: "Cartão de Crédito", "PIX", "Dinheiro"
    
    @Column(name = "status_Pagamento", nullable = false)
    private String statusPagamento;

    //Construtor
    public FormaPagamento() {
    }

    public FormaPagamento(Long idFormaPagamento, String formaPagamento, String statusPagamento) {
        this.idFormaPagamento = idFormaPagamento;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = statusPagamento;
    }

    //Getters e Setters

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
