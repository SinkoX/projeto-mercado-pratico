package com.senaidev.prjMercadoPratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Produto;

@Repository

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeProdutoContainingIgnoreCase(String nome);
    
    List<Produto> findByCategoriaIgnoreCase(String categoria);
    
    List<Produto> findByDataValidadeBefore(LocalDate data);
}
