package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    private LocalDate tempoEntrega;

    // 🔹 Construtor padrão (sem argumentos)
    public Entrega() {
    }

    // 🔹 Construtor com todos os argumentos
    public Entrega(Long idEntrega, LocalDate tempoEntrega) {
        this.idEntrega = idEntrega;
        this.tempoEntrega = tempoEntrega;
    }

    // 🔹 Getters & Setters
    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public LocalDate getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(LocalDate tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }
}