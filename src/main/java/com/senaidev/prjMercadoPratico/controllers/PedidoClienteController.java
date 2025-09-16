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

    // GET /pedidos-cliente/status?valor=Aguardando
    @GetMapping("/status")
    public ResponseEntity<List<PedidoCliente>> findByStatus(@RequestParam String valor) {
        return ResponseEntity.ok(pedidoClienteService.findByStatus(valor));
    }

    // GET /pedidos-cliente/data?data=2025-09-16
    @GetMapping("/data")
    public ResponseEntity<List<PedidoCliente>> findByDataPedido(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(pedidoClienteService.findByDataPedido(data));
    }

    // GET /pedidos-cliente/periodo?inicio=2025-09-01&fim=2025-09-16
    @GetMapping("/periodo")
    public ResponseEntity<List<PedidoCliente>> findByDataPedidoBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(pedidoClienteService.findByDataPedidoBetween(inicio, fim));
    }
// ERROO 
    // GET /pedidos-cliente/funcionario/{idFuncionario}
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<List<PedidoCliente>> findByFuncionarioId(@PathVariable Long idFuncionario) {
        return ResponseEntity.ok(pedidoClienteService.findByFuncionarioId(idFuncionario));
    }//

    // GET /pedidos-cliente/pagamento/{idFormaPagamento}
    @GetMapping("/pagamento/{idFormaPagamento}")
    public ResponseEntity<List<PedidoCliente>> findByFormaPagamentoId(@PathVariable Long idFormaPagamento) {
        return ResponseEntity.ok(pedidoClienteService.findByFormaPagamentoId(idFormaPagamento));
    }

    // GET /pedidos-cliente/usuario-status?idUsuario=1&status=Entregue
    @GetMapping("/usuario-status")
    public ResponseEntity<List<PedidoCliente>> findByUsuarioIdAndStatus(
            @RequestParam Long idUsuario,
            @RequestParam String status) {
        return ResponseEntity.ok(pedidoClienteService.findByUsuarioIdAndStatus(idUsuario, status));
    }

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
