package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.ItemCarrinho;
import com.senaidev.prjMercadoPratico.repositories.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {

	@Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

	//Busca por todos os itens do Carrinho
    public List<ItemCarrinho> findAll() {
        return itemCarrinhoRepository.findAll();
    }

    //Busca por ID do item do Carrinho
    public ItemCarrinho findById(Long id) {
        return itemCarrinhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do Carrinho não encontrado com ID: " + id));
    }

    //Cria um novo item no Carrinho
    public ItemCarrinho save(ItemCarrinho itemCarrinho) {
        return itemCarrinhoRepository.save(itemCarrinho);
    }
    
    //Atualiza um item no Carrinho
    public ItemCarrinho update(Long id, ItemCarrinho novoItem) {
        ItemCarrinho itemCarrinhoExistente = findById(id);

        itemCarrinhoExistente.setProduto(novoItem.getProduto());
        itemCarrinhoExistente.setQuantidade(novoItem.getQuantidade());
        itemCarrinhoExistente.setSubTotal(novoItem.getSubTotal());
        return itemCarrinhoRepository.save(itemCarrinhoExistente);
    }

    //Deleta um item no Carrinho
    public void delete(Long id) {
        if (!itemCarrinhoRepository.existsById(id)) {
            throw new RuntimeException("Item do Carrinho não encontrado com ID: " + id);
        }
        itemCarrinhoRepository.deleteById(id);
    }
}
