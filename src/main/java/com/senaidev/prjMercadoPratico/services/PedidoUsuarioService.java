package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.repositories.PedidoUsuarioRepository;


@Service
public class PedidoUsuarioService {

    @Autowired
    private PedidoUsuarioRepository pedidoUsuarioRepository;

    public List<PedidoUsuario> findAll() {
        return pedidoUsuarioRepository.findAll();
    }

    public PedidoUsuario findById(Long id) {
        return pedidoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<PedidoUsuario> findByUsuarioId(Long id) {
        return pedidoUsuarioRepository.findByUsuario_IdUsuario(id);
    }

   

    public PedidoUsuario insert(PedidoUsuario pedidoUsuario) {
        return pedidoUsuarioRepository.save(pedidoUsuario);
    }

    public PedidoUsuario update(Long id, PedidoUsuario novoPedido) {
        PedidoUsuario pedido = findById(id);
        pedido.setStatus(novoPedido.getStatus());
        pedido.setDataPedidoUsuario(novoPedido.getDataPedidoUsuario());
        pedido.setUsuario(novoPedido.getUsuario());
        pedido.setFuncionario(novoPedido.getFuncionario());
        return pedidoUsuarioRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoUsuarioRepository.deleteById(id);
    }
}
