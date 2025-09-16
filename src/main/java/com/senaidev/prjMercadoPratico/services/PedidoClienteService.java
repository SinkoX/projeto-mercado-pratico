package com.senaidev.prjMercadoPratico.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.PedidoCliente;
import com.senaidev.prjMercadoPratico.repositories.PedidoClienteRepository;

@Service
public class PedidoClienteService {

    @Autowired
    private PedidoClienteRepository pedidoClienteRepository;

    public List<PedidoCliente> findAll() {
        return pedidoClienteRepository.findAll();
    }

    public PedidoCliente findById(Long id) {
        return pedidoClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<PedidoCliente> findByUsuarioId(Long idUsuario) {
        return pedidoClienteRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<PedidoCliente> findByStatus(String status) {
        return pedidoClienteRepository.findByStatusIgnoreCase(status);
    }

    public List<PedidoCliente> findByDataPedido(LocalDate dataPedido) {
        return pedidoClienteRepository.findByDataPedido(dataPedido);
    }

    public List<PedidoCliente> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim) {
        return pedidoClienteRepository.findByDataPedidoBetween(dataInicio, dataFim);
    }

    public List<PedidoCliente> findByFuncionarioId(Long idFuncionario) {
        return pedidoClienteRepository.findByFuncionarioIdFuncionario(idFuncionario);
    }

    public List<PedidoCliente> findByFormaPagamentoId(Long idFormaPagamento) {
        return pedidoClienteRepository.findByFormaPagamentoIdFormaPagamento(idFormaPagamento);
    }

    public List<PedidoCliente> findByUsuarioIdAndStatus(Long idUsuario, String status) {
        return pedidoClienteRepository.findByUsuarioIdUsuarioAndStatusIgnoreCase(idUsuario, status);
    }

    public PedidoCliente insert(PedidoCliente pedidoCliente) {
        return pedidoClienteRepository.save(pedidoCliente);
    }

    public PedidoCliente update(Long id, PedidoCliente novoPedido) {
        PedidoCliente pedido = findById(id);
        pedido.setStatus(novoPedido.getStatus());
        pedido.setDataPedido(novoPedido.getDataPedido());
        pedido.setUsuario(novoPedido.getUsuario());
        pedido.setFuncionario(novoPedido.getFuncionario());
        pedido.setFormaPagamento(novoPedido.getFormaPagamento());
        // Atualize outros campos conforme necessário
        return pedidoClienteRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoClienteRepository.deleteById(id);
    }
}
