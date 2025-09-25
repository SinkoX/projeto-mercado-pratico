package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Subcategoria;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long>{

	// Buscar funcionário por nome da subcategoria
    List<Subcategoria> findByNomeSubcategoria(String nomeSubcategoria);
    
    // Buscar por nome ignorando maiúsculas/minúsculas
    List<Subcategoria> findByNomeSubcategoriaIgnoreCase(String nomeSubcategoria);
}
