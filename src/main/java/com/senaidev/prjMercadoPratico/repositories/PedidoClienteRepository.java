package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.PedidoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoClienteRepository extends JpaRepository<PedidoCliente, Long> {

    // Buscar pedidos por ID de cliente (usuário)
    List<PedidoCliente> findByUsuarioIdUsuario(Long idUsuario);

    // Buscar pedidos por status (ignora maiúsculas/minúsculas)
    List<PedidoCliente> findByStatusIgnoreCase(String status);

    // Buscar pedidos por data exata
    List<PedidoCliente> findByDataPedido(LocalDate dataPedido);

    // Buscar pedidos entre duas datas
    List<PedidoCliente> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);

    // Buscar pedidos por funcionário
    List<PedidoCliente> findByUsuarioIdUsuario1(Long idUsuario);

    // Buscar pedidos por forma de pagamento
    List<PedidoCliente> findByFormaPagamentoIdFormaPagamento(Long idFormaPagamento);

    // Buscar pedidos de um cliente por status
    List<PedidoCliente> findByUsuarioIdUsuarioAndStatusIgnoreCase(Long idUsuario, String status);
    
    List<PedidoCliente> findByFuncionarioIdFuncionario(Long idFuncionario);

}
