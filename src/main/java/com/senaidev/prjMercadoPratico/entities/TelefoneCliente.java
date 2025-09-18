package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.Column;
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
    @Column(name = "id_Telefone_Cliente", nullable = false)
    private Long idTelefoneCliente;

    @Column(name = "numero_Telefone_Cliente", nullable = false, length = 15)
    private String numeroTelefoneCliente;

    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    // Construtor padrão
    public TelefoneCliente() {
    }

    // Construtor com todos os campos
    public TelefoneCliente(Long idTelefoneCliente, String numeroTelefoneCliente, Usuario usuario) {
        this.idTelefoneCliente = idTelefoneCliente;
        this.numeroTelefoneCliente = numeroTelefoneCliente;
        this.usuario = usuario;
    }

    // Getters & Setters

    public Long getIdTelefoneCliente() {
        return idTelefoneCliente;
    }

    public void setIdTelefoneCliente(Long idTelefoneCliente) {
        this.idTelefoneCliente = idTelefoneCliente;
    }

    public String getNumeroTelefoneCliente() {
        return numeroTelefoneCliente;
    }

    public void setNumeroTelefoneCliente(String numeroTelefoneCliente) {
        this.numeroTelefoneCliente = numeroTelefoneCliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
