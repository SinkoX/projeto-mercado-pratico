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
	@Column(name = "id_Endereco", nullable = false)
	private Long idEndereco;
			
	@Column(name = "cep", nullable = false, length = 100)
	private String cep;
			
	@Column(name = "rua", nullable = false, length = 100)
	private String rua;
			
	@Column(name = "numero", nullable = false)
	private String numero;
			
	@Column(name = "bairro", nullable = false, length = 100)
	private String bairro;
			
	@Column(name = "cidade", nullable = false, length = 100)
	private String cidade;
			
	@Column(name = "complemento", length = 100)
	private String complemento;
			
	@ManyToOne
    @JsonBackReference
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Endereco() {

	}

	public Endereco(Long id, String rua, String numero, String bairro, String cidade, String cep, String complemento) {
		this.idEndereco = id;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.complemento = complemento;
	}


	public Long getId_endereco() {
		return idEndereco;
	}

	public void setId_endereco(Long id_endereco) {
		this.idEndereco = id_endereco;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}