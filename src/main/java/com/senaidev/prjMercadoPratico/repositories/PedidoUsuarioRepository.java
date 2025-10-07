package com.senaidev.prjMercadoPratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;


@Repository
public interface PedidoUsuarioRepository extends JpaRepository<PedidoUsuario, Long> {

    // Buscar pedidos por ID de cliente (usuário)
    List<PedidoUsuario> findByUsuarioId(Long id);

    // Buscar pedidos por status (ignora maiúsculas/minúsculas)
    List<PedidoUsuario> findByStatusIgnoreCase(String status);

    // Buscar pedidos por data exata
    List<PedidoUsuario> findByDataPedidoUsuario(LocalDate dataPedidoUsuario);

    // Buscar pedidos por forma de pagamento
    List<PedidoUsuario> findByFormaPagamentoIdFormaPagamento(Long idFormaPagamento);

    // Buscar pedidos de um cliente por status
    List<PedidoUsuario> findByUsuarioIdAndStatusIgnoreCase(Long id, String status);
    



}
