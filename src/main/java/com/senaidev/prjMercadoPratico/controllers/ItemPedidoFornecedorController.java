package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.entities.ItemPedidoFornecedor;
import com.senaidev.prjMercadoPratico.services.ItemPedidoFornecedorService;

@RestController
@RequestMapping("/itens-pedido-fornecedor")
public class ItemPedidoFornecedorController {

    private final ItemPedidoFornecedorService service;

    public ItemPedidoFornecedorController(ItemPedidoFornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoFornecedor>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoFornecedor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemPedidoFornecedor> criar(@RequestBody ItemPedidoFornecedor item) {
        return ResponseEntity.ok(service.criar(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoFornecedor> atualizar(@PathVariable Long id,
                                                          @RequestBody ItemPedidoFornecedor atualizado) {
        return ResponseEntity.ok(service.atualizar(id, atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
