package com.senaidev.prjMercadoPratico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

	// Busca o carrinho de um usuário com seus itens carregados (fetch join)
    @Query("SELECT c FROM Carrinho c LEFT JOIN FETCH c.itensCarrinho WHERE c.usuario.idUsuario = :idUsuario")
    Optional<Carrinho> buscarCarrinhoPorUsuario(@Param("idUsuario") Long idUsuario);
}
