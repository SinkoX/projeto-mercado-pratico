package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	// Buscar funcionário por nome da subcategoria
    List<Categoria> findByNomeCategoria(String nomeCategoria);
    
    // Buscar por nome ignorando maiúsculas/minúsculas
    List<Categoria> findByNomeCategoriaIgnoreCase(String nomeCategoria);
}
