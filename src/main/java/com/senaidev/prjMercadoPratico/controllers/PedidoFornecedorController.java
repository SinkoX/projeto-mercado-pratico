package com.senaidev.prjMercadoPratico.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import com.senaidev.prjMercadoPratico.services.PedidoFornecedorService;

@RestController
@RequestMapping("/pedidos-fornecedor")
public class PedidoFornecedorController {

    @Autowired
    private PedidoFornecedorService pedidoFornecedorService;

    // GET /pedidos-fornecedor
    @GetMapping
    public ResponseEntity<List<PedidoFornecedor>> findAll() {
        return ResponseEntity.ok(pedidoFornecedorService.findAll());
    }

    // GET /pedidos-fornecedor/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PedidoFornecedor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoFornecedorService.findById(id));
    }

    // POST /pedidos-fornecedor
    @PostMapping
    public ResponseEntity<PedidoFornecedor> insert(@RequestBody PedidoFornecedor pedido) {
        return ResponseEntity.ok(pedidoFornecedorService.insert(pedido));
    }

    // PUT /pedidos-fornecedor/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PedidoFornecedor> update(@PathVariable Long id, @RequestBody PedidoFornecedor novoPedido) {
        return ResponseEntity.ok(pedidoFornecedorService.update(id, novoPedido));
    }

    // DELETE /pedidos-fornecedor/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoFornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /pedidos-fornecedor/fornecedor/{idFornecedor}
    @GetMapping("/fornecedor/{idFornecedor}")
    public ResponseEntity<List<PedidoFornecedor>> findByFornecedor(@PathVariable Long idFornecedor) {
        return ResponseEntity.ok(pedidoFornecedorService.findByFornecedor(idFornecedor));
    }

    // GET /pedidos-fornecedor/produto/{idProduto}
    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<PedidoFornecedor>> findByProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(pedidoFornecedorService.findByProduto(idProduto));
    }

    // GET /pedidos-fornecedor/data?data=2025-09-16
    @GetMapping("/data")
    public ResponseEntity<List<PedidoFornecedor>> findByData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(pedidoFornecedorService.findByData(data));
    }
}
