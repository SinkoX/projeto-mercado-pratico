package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Entrega", nullable = false)
    private Long idEntrega;

    @Column(name = "tempo_Entrega", nullable = false)
    private LocalDate tempoEntrega;

    //Construtor
    public Entrega() {
    }

    
    public Entrega(Long idEntrega, LocalDate tempoEntrega) {
        this.idEntrega = idEntrega;
        this.tempoEntrega = tempoEntrega;
    }

    //Getters & Setters
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