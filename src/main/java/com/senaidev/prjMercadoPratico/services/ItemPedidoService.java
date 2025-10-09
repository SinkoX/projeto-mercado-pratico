package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.ItemPedido;
import com.senaidev.prjMercadoPratico.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
    private ItemPedidoRepository itemPedidoRepository;

	//Busca por todos os itens do Pedido
    public List<ItemPedido> findAll() {
        return itemPedidoRepository.findAll();
    }

    //Busca por ID do item do Pedido
    public ItemPedido findById(Long id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do Pedido não encontrado com ID: " + id));
    }

    //Cria um novo item no Pedido
    public ItemPedido save(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }
    
    //Atualiza um item do Pedido
    public ItemPedido update(Long id, ItemPedido novoItem) {
        ItemPedido itemPedidoExistente = findById(id);

        itemPedidoExistente.setProduto(novoItem.getProduto());
        itemPedidoExistente.setQuantidade(novoItem.getQuantidade());
        itemPedidoExistente.setSubTotal(novoItem.getSubTotal());
        return itemPedidoRepository.save(itemPedidoExistente);
    }

    //Deleta um item do Pedido
    public void delete(Long id) {
        if (!itemPedidoRepository.existsById(id)) {
            throw new RuntimeException("Item do Pedido não encontrado com ID: " + id);
        }
        itemPedidoRepository.deleteById(id);
    }
}
