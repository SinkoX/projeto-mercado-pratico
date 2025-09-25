package com.senaidev.prjMercadoPratico.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_categoria")
public class Categoria {
	
	//Atributos
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Categoria")
    private Long idCategoria;
	
	@Column(name = "nome_Categoria", nullable = false, length = 100)
    private String nomeCategoria;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Subcategoria> subcategorias;
	
	//Construtores
	public Categoria(){
		
	}
	
	public Categoria(Long idCategoria, String nomeCategoria){
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	//Getters e Setters
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
}
