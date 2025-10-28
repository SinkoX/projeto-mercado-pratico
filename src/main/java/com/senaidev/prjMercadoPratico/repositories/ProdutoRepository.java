package com.senaidev.prjMercadoPratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // 🔍 Busca por nome (sem case sensitive)
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);

    // 🧩 Busca produtos por Categoria
    List<Produto> findByCategoria(Categoria categoria);

    // ✅ Busca produtos pelo ID da Categoria
    @Query("SELECT p FROM Produto p WHERE p.categoria.idCategoria = :idCategoria")
    List<Produto> findByCategoriaId(@Param("idCategoria") Long idCategoria);

    // 🕒 Busca produtos com validade anterior a uma data específica
    List<Produto> findByDataValidadeBefore(LocalDate dataValidade);
}
