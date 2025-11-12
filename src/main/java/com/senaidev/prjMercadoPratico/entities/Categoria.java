package com.senaidev.prjMercadoPratico.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nome_categoria", nullable = false, length = 100)
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // evita loop, pois Produto e Subcategoria já mostram Categoria
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // permite mostrar subcategorias dentro da categoria
    private List<Subcategoria> subcategorias = new ArrayList<>();

    // Construtores
    public Categoria() {
    }

    public Categoria(Long idCategoria, String nomeCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    // Getters e Setters
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    // Métodos auxiliares
    public void addProduto(Produto produto) {
        produtos.add(produto);
        produto.setCategoria(this);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
        produto.setCategoria(null);
    }
    
    public void addSubcategoria(Subcategoria subcategoria) {
        subcategorias.add(subcategoria);
        subcategoria.setCategoria(this);
    }

    public void removeSubcategoria(Subcategoria subcategoria) {
        subcategorias.remove(subcategoria);
        subcategoria.setCategoria(null);
    }
}
