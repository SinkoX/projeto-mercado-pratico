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

    public List<PedidoCliente> findByFuncionarioId(Long idFuncionario) {
        return pedidoClienteRepository.findByFuncionarioIdUsuario(idFuncionario);
    }

    public PedidoCliente insert(PedidoCliente pedidoCliente) {
        return pedidoClienteRepository.save(pedidoCliente);
    }

    public PedidoCliente update(Long id, PedidoCliente novoPedido) {
        PedidoCliente pedido = findById(id);
        pedido.setStatusPedidoCliente(novoPedido.getStatusPedidoCliente());
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
