package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import com.senaidev.prjMercadoPratico.enums.StatusPedidoFornecedor;
import com.senaidev.prjMercadoPratico.repositories.PedidoFornecedorRepository;

@Service
public class PedidoFornecedorService {

    private final PedidoFornecedorRepository pedidoFornecedorRepository;
    
    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    public PedidoFornecedorService(PedidoFornecedorRepository pedidoFornecedorRepository) {
        this.pedidoFornecedorRepository = pedidoFornecedorRepository;
    }

    public List<PedidoFornecedor> listarTodos() {
        return pedidoFornecedorRepository.findAll();
    }

    public PedidoFornecedor buscarPorId(Long id) {
        return pedidoFornecedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido do fornecedor não encontrado com ID: " + id));
    }

    public PedidoFornecedor criar(PedidoFornecedor pedidoFornecedor) {
        return pedidoFornecedorRepository.save(pedidoFornecedor);
    }

    @Transactional
    public PedidoFornecedor atualizarStatus(Long idPedido, StatusPedidoFornecedor novoStatus) {
        PedidoFornecedor pedido = buscarPorId(idPedido);
        pedido.atualizarStatus(novoStatus);
        
        // 🔹 Se o status mudou para RECEBIDO, registra entrada no estoque
        if (novoStatus == StatusPedidoFornecedor.RECEBIDO) {
            movimentacaoEstoqueService.registrarEntrada(pedido);
        }
        
        return pedidoFornecedorRepository.save(pedido);
    }

    public void deletar(Long idPedido) {
        PedidoFornecedor pedido = buscarPorId(idPedido);
        pedidoFornecedorRepository.delete(pedido);
    }
}
