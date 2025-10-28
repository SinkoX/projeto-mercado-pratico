package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;


@Repository
public interface PedidoUsuarioRepository extends JpaRepository<PedidoUsuario, Long> {

	// 🔍 Busca todos os pedidos de um usuário específico
    List<PedidoUsuario> findByUsuarioIdUsuario(Long idUsuario);
}
