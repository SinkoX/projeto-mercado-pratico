package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import com.senaidev.prjMercadoPratico.repositories.PedidoFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoFornecedorService {

    @Autowired
    private PedidoFornecedorRepository pedidoFornecedorRepository;

    // Buscar todos os pedidos
    public List<PedidoFornecedor> findAll() {
        return pedidoFornecedorRepository.findAll();
    }

    //Buscar por ID
    public PedidoFornecedor findById(Long id) {
        Optional<PedidoFornecedor> pedido = pedidoFornecedorRepository.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException("Pedido de fornecedor não encontrado com ID: " + id));
    }

    //Inserir novo pedido
    public PedidoFornecedor insert(PedidoFornecedor pedido) {
        return pedidoFornecedorRepository.save(pedido);
    }

    // Atualizar pedido
    public PedidoFornecedor update(Long id, PedidoFornecedor novoPedido) {
        PedidoFornecedor pedido = findById(id);
        pedido.setDataPedidoFornecedor(novoPedido.getDataPedidoFornecedor());
        pedido.setFornecedor(novoPedido.getFornecedor());
        pedido.setProduto(novoPedido.getProduto());
        return pedidoFornecedorRepository.save(pedido);
    }

    //Deletar por ID
    public void delete(Long id) {
        pedidoFornecedorRepository.deleteById(id);
    }

    //Buscar por fornecedor
    public List<PedidoFornecedor> findByFornecedor(Long idFornecedor) {
        return pedidoFornecedorRepository.findByFornecedorIdFornecedor(idFornecedor);
    }

    //Buscar por produto
    public List<PedidoFornecedor> findByProduto(Long idProduto) {
        return pedidoFornecedorRepository.findByProdutoIdProduto(idProduto);
    }

    //Buscar por data específica
    public List<PedidoFornecedor> findByData(LocalDate data) {
        return pedidoFornecedorRepository.findByDataPedidoFornecedor(data);
    }
}
