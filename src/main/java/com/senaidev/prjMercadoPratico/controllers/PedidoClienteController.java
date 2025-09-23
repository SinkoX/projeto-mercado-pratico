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

import com.senaidev.prjMercadoPratico.entities.PedidoCliente;
import com.senaidev.prjMercadoPratico.services.PedidoClienteService;

@RestController
@RequestMapping("/pedidos-cliente")
public class PedidoClienteController {

    @Autowired
    private PedidoClienteService pedidoClienteService;

    // GET /pedidos-cliente
    @GetMapping
    public ResponseEntity<List<PedidoCliente>> findAll() {
        return ResponseEntity.ok(pedidoClienteService.findAll());
    }

    // GET /pedidos-cliente/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PedidoCliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoClienteService.findById(id));
    }

    // GET /pedidos-cliente/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PedidoCliente>> findByUsuarioId(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(pedidoClienteService.findByUsuarioId(idUsuario));
    }

// ERROO 
    // GET /pedidos-cliente/funcionario/{idFuncionario}
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<List<PedidoCliente>> findByFuncionarioId(@PathVariable Long idFuncionario) {
        return ResponseEntity.ok(pedidoClienteService.findByFuncionarioId(idFuncionario));
    }//

    // POST /pedidos-cliente
    @PostMapping
    public ResponseEntity<PedidoCliente> insert(@RequestBody PedidoCliente pedidoCliente) {
        return ResponseEntity.ok(pedidoClienteService.insert(pedidoCliente));
    }

    // PUT /pedidos-cliente/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PedidoCliente> update(@PathVariable Long id, @RequestBody PedidoCliente pedidoAtualizado) {
        return ResponseEntity.ok(pedidoClienteService.update(id, pedidoAtualizado));
    }

    // DELETE /pedidos-cliente/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoClienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
