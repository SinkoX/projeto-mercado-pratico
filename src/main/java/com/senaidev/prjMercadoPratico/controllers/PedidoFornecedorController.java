package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.PedidoFornecedorDTO;
import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import com.senaidev.prjMercadoPratico.enums.StatusPedidoFornecedor;
import com.senaidev.prjMercadoPratico.services.PedidoFornecedorService;

@RestController
@RequestMapping("/pedidos-fornecedores")
public class PedidoFornecedorController {

    private final PedidoFornecedorService pedidoFornecedorService;

    public PedidoFornecedorController(PedidoFornecedorService pedidoFornecedorService) {
        this.pedidoFornecedorService = pedidoFornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoFornecedorDTO>> listarTodos() {
        List<PedidoFornecedorDTO> pedidos = pedidoFornecedorService.listarTodos().stream()
                .map(PedidoFornecedorDTO::new)
                .toList();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoFornecedorDTO> buscarPorId(@PathVariable Long id) {
        PedidoFornecedor pedido = pedidoFornecedorService.buscarPorId(id);
        return ResponseEntity.ok(new PedidoFornecedorDTO(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoFornecedorDTO> criar(@RequestBody PedidoFornecedor pedidoFornecedor) {
        PedidoFornecedor novoPedido = pedidoFornecedorService.criar(pedidoFornecedor);
        return ResponseEntity.ok(new PedidoFornecedorDTO(novoPedido));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoFornecedorDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedidoFornecedor novoStatus) {
        PedidoFornecedor pedidoAtualizado = pedidoFornecedorService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(new PedidoFornecedorDTO(pedidoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoFornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
