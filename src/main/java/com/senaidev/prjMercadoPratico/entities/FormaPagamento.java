package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_forma_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pagamento")
    private Long idFormaPagamento;

    @Column(name = "tipo_pagamento", nullable = false, length = 50)
    private String tipo; // Ex: "Cartão de Crédito", "PIX", "Dinheiro"

    //Construtor
    public FormaPagamento() {
    }

    public FormaPagamento(Long idFormaPagamento, String tipo) {
        this.idFormaPagamento = idFormaPagamento;
        this.tipo = tipo;
    }

    //Getters e Setters

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
