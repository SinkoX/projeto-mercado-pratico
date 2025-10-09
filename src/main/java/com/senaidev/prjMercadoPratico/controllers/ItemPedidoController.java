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

import com.senaidev.prjMercadoPratico.entities.ItemPedido;
import com.senaidev.prjMercadoPratico.services.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	//Busca por todos os Itens do Carrinho
	@GetMapping
    public ResponseEntity<List<ItemPedido>> findAll() {
        return ResponseEntity.ok(itemPedidoService.findAll());
    }

	//Busca por um Item do Carrinho por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoService.findById(id));
    }

    //Posta um Item do Carrinho
    @PostMapping
    public ResponseEntity<ItemPedido> save(@RequestBody ItemPedido itemPedido) {
        ItemPedido novoItem = itemPedidoService.save(itemPedido);
        return ResponseEntity.ok(novoItem);
    }
    
    //Atualiza um Item do Carrinho
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> update(@PathVariable Long id, @RequestBody ItemPedido itemPedidoAtualizado) {
        ItemPedido novoItem = itemPedidoService.update(id, itemPedidoAtualizado);
        return ResponseEntity.ok(novoItem);
    }

    //Deleta um Item do Carrinho
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
