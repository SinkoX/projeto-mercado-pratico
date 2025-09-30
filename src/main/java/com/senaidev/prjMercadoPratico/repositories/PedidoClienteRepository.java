package com.senaidev.prjMercadoPratico.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.PedidoCliente;

@Repository
public interface PedidoClienteRepository extends JpaRepository<PedidoCliente, Long> {

    // Buscar pedidos por ID de cliente (usuário)
    List<PedidoCliente> findByUsuarioId(Long id);

    // Buscar pedidos por status (ignora maiúsculas/minúsculas)
    List<PedidoCliente> findByStatusIgnoreCase(String status);

    // Buscar pedidos por data exata
    List<PedidoCliente> findByDataPedido(LocalDate dataPedido);

    // Buscar pedidos por forma de pagamento
    List<PedidoCliente> findByFormaPagamentoIdFormaPagamento(Long idFormaPagamento);

    // Buscar pedidos de um cliente por status
    List<PedidoCliente> findByUsuarioIdAndStatusIgnoreCase(Long id, String status);
    



}
