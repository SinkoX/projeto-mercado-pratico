package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_telefone_cliente")
public class TelefoneCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTelefone;

    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    // Construtor padrão
    public TelefoneCliente() {
    }

    // Construtor com todos os campos
    public TelefoneCliente(Long idTelefone, String numeroTelefone, Cliente cliente) {
        this.idTelefone = idTelefone;
        this.numeroTelefone = numeroTelefone;
        this.cliente = cliente;
    }

    // Getters & Setters

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
