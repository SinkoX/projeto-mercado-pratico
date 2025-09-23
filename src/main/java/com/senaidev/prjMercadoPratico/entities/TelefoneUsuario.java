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
@Table(name = "tb_telefone_usuario")
public class TelefoneUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Telefone_Usuario", nullable = false)
    private Long idTelefoneUsuario;

    @Column(name = "numero_Telefone_Usuario", nullable = false, length = 15)
    private String numeroTelefoneUsuario;

    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    // Construtor padrão
    public TelefoneUsuario() {
    }

    // Construtor com todos os campos
    public TelefoneUsuario(Long idTelefoneUsuario, String numeroTelefoneUsuario, Usuario usuario) {
        this.idTelefoneUsuario = idTelefoneUsuario;
        this.numeroTelefoneUsuario = numeroTelefoneUsuario;
        this.usuario = usuario;
    }

    //Getters e Setters
	public Long getIdTelefoneUsuario() {
		return idTelefoneUsuario;
	}

	public void setIdTelefoneUsuario(Long idTelefoneUsuario) {
		this.idTelefoneUsuario = idTelefoneUsuario;
	}

	public String getNumeroTelefoneUsuario() {
		return numeroTelefoneUsuario;
	}

	public void setNumeroTelefoneUsuario(String numeroTelefoneUsuario) {
		this.numeroTelefoneUsuario = numeroTelefoneUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
