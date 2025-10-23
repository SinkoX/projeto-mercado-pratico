package com.senaidev.prjMercadoPratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // 🔍 Busca por nome (sem case sensitive)
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);

    // 🧩 Busca produtos por Subcategoria
    List<Produto> findBySubcategoria(Subcategoria subcategoria);

    // 🧩 Busca produtos por Categoria (navegando pela Subcategoria)
    List<Produto> findBySubcategoria_Categoria(Categoria categoria);

    // ✅ Busca produtos pelo ID da Categoria (mais confiável)
    @Query("SELECT p FROM Produto p WHERE p.subcategoria.categoria.idCategoria = :idCategoria")
    List<Produto> findByCategoriaId(@Param("idCategoria") Long idCategoria);

    // 🕒 Busca produtos com validade anterior a uma data específica
    List<Produto> findByDataValidadeBefore(LocalDate dataValidade);
}
