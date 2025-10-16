package com.senaidev.prjMercadoPratico.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.entities.Carrinho;
import com.senaidev.prjMercadoPratico.entities.ItemCarrinho;
import com.senaidev.prjMercadoPratico.repositories.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {

    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final CarrinhoService carrinhoService;

    public ItemCarrinhoService(ItemCarrinhoRepository itemCarrinhoRepository, CarrinhoService carrinhoService) {
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.carrinhoService = carrinhoService;
    }

    @Transactional
    public ItemCarrinho atualizarQuantidade(Long idUsuario, Long idItem, Integer novaQuantidade) {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorUsuario(idUsuario);
        ItemCarrinho item = carrinho.getItensCarrinho().stream()
                .filter(i -> i.getIdItemCarrinho().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado no carrinho"));

        item.setQuantidade(novaQuantidade);
        item.setSubTotal(item.getPrecoUnitario().multiply(java.math.BigDecimal.valueOf(novaQuantidade)));
        carrinho.atualizarTotais();
        return itemCarrinhoRepository.save(item);
    }
}
