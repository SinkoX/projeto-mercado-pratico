package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.ItemCarrinho;
import com.senaidev.prjMercadoPratico.services.ItemCarrinhoService;

@RestController
@RequestMapping("/itens-carrinho")
public class ItemCarrinhoController {

	@Autowired
	private ItemCarrinhoService itemCarrinhoService;

	//Busca por todos os Itens do Carrinho
	@GetMapping
    public ResponseEntity<List<ItemCarrinho>> findAll() {
        return ResponseEntity.ok(itemCarrinhoService.findAll());
    }

	//Busca por um Item do Carrinho por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemCarrinho> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemCarrinhoService.findById(id));
    }

    //Posta um Item do Carrinho
    @PostMapping
    public ResponseEntity<ItemCarrinho> save(@RequestBody ItemCarrinho itemCarrinho) {
        ItemCarrinho novoItem = itemCarrinhoService.save(itemCarrinho);
        return ResponseEntity.ok(novoItem);
    }
    
    //Atualiza um Item do Carrinho
    @PutMapping("/{id}")
    public ResponseEntity<ItemCarrinho> update(@PathVariable Long id, @RequestBody ItemCarrinho itemCarrinhoAtualizado) {
        ItemCarrinho novoItem = itemCarrinhoService.update(id, itemCarrinhoAtualizado);
        return ResponseEntity.ok(novoItem);
    }

    //Deleta um Item do Carrinho
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemCarrinhoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
