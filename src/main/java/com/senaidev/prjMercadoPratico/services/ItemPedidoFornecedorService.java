package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.ItemPedidoFornecedor;
import com.senaidev.prjMercadoPratico.repositories.ItemPedidoFornecedorRepository;

@Service
public class ItemPedidoFornecedorService {

    private final ItemPedidoFornecedorRepository repository;

    public ItemPedidoFornecedorService(ItemPedidoFornecedorRepository repository) {
        this.repository = repository;
    }

    public List<ItemPedidoFornecedor> listarTodos() {
        return repository.findAll();
    }

    public ItemPedidoFornecedor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ItemPedidoFornecedor não encontrado: " + id));
    }

    public ItemPedidoFornecedor criar(ItemPedidoFornecedor item) {
        return repository.save(item);
    }

    public ItemPedidoFornecedor atualizar(Long id, ItemPedidoFornecedor novo) {
        ItemPedidoFornecedor existente = buscarPorId(id);

        existente.setQuantidade(novo.getQuantidade());
        existente.setProduto(novo.getProduto());
        existente.setSubTotal(novo.getSubTotal());
        existente.setPedidoFornecedor(novo.getPedidoFornecedor());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
