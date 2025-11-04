package com.senaidev.prjMercadoPratico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Estoque findByProdutoIdProduto(Long idProduto);
}
