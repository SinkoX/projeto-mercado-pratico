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
@Table(name = "tb_subcategoria")
public class Subcategoria{

	// Atributos
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria")
    private Long idSubcategoria;
	
	@Column(name = "nome_subcategoria", nullable = false, length = 100)
    private String nomeSubcategoria;
	
	@ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
	
	// Construtores
	public Subcategoria() {
		
	}

	public Subcategoria(Long idSubcategoria, String nomeSubcategoria, Categoria categoria) {
		this.idSubcategoria = idSubcategoria;
		this.nomeSubcategoria = nomeSubcategoria;
		this.categoria = categoria;
	}

	// Getters e Setters
	public Long getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(Long idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public String getNomeSubcategoria() {
		return nomeSubcategoria;
	}

	public void setNomeSubcategoria(String nomeSubcategoria) {
		this.nomeSubcategoria = nomeSubcategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
