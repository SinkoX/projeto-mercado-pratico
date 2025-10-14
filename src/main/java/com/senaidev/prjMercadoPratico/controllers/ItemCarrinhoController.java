package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.ItemCarrinhoDTO;
import com.senaidev.prjMercadoPratico.entities.ItemCarrinho;
import com.senaidev.prjMercadoPratico.services.ItemCarrinhoService;

@RestController
@RequestMapping("/itens-carrinho")
public class ItemCarrinhoController {

    private final ItemCarrinhoService itemCarrinhoService;

    public ItemCarrinhoController(ItemCarrinhoService itemCarrinhoService) {
        this.itemCarrinhoService = itemCarrinhoService;
    }

    @PutMapping("/{idUsuario}/atualizar/{idItem}")
    public ResponseEntity<ItemCarrinhoDTO> atualizarQuantidade(@PathVariable Long idUsuario,
                                                               @PathVariable Long idItem,
                                                               @RequestParam Integer quantidade) {
        ItemCarrinho item = itemCarrinhoService.atualizarQuantidade(idUsuario, idItem, quantidade);
        return ResponseEntity.ok(new ItemCarrinhoDTO(item));
    }
}
