package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Estoque;
import com.senaidev.prjMercadoPratico.entities.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    // Busca estoque por produto
    Optional<Estoque> findByProduto(Produto produto);
    
    Optional<Estoque> findByProdutoIdProduto(Long idProduto);

    // Busca produtos com estoque abaixo do mínimo
    @Query("SELECT e FROM Estoque e WHERE e.quantidade < e.quantidadeMinima")
    List<Estoque> findEstoqueAbaixoDoMinimo();

    // Busca produtos sem estoque
    @Query("SELECT e FROM Estoque e WHERE e.quantidade = 0")
    List<Estoque> findEstoqueZerado();
}