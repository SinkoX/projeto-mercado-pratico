package com.senaidev.prjMercadoPratico.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_endereco")

public class Endereco {
	//Atributos
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id_endereco;
			
			@Column(name = "cep", length = 100)
			private String cep;
			
			@Column(name = "rua", length = 100)
			private String rua;
			
			@Column(name = "numero",nullable = false)
			private String numero;
			
			@Column(name = "bairro",length = 100)
			private String bairro;
			
			@Column(name = "cidade", length = 100)
			private String cidade;
			
			@Column(name = "estado", nullable = false, length = 50)
			private String estado;
			
			@Column(name = "complemento", length = 100)
			private String complemento;
			
			@ManyToOne
		    @JsonBackReference
		    @JoinColumn(name = "CLIENTE_id_cliente")
		    private Cliente cliente;

	public Endereco() {

	}

	public Endereco(Long id, String rua, String numero, String bairro, String cidade, String estado, String cep, String complemento) {
		this.id_endereco = id;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
	}


	public Long getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(Long id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cliente getUsuario() {
		return cliente;
	}

	public void setUsuario(Cliente cliente) {
		this.cliente = cliente;
	}

	}