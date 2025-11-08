package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.PedidoUsuarioDTO;
import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.enums.StatusPedido;
import com.senaidev.prjMercadoPratico.services.PedidoUsuarioService;

@RestController
@RequestMapping("/pedidos-usuarios")
public class PedidoUsuarioController {

    private final PedidoUsuarioService pedidoUsuarioService;

    public PedidoUsuarioController(PedidoUsuarioService pedidoUsuarioService) {
        this.pedidoUsuarioService = pedidoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoUsuarioDTO>> listarTodos() {
        List<PedidoUsuarioDTO> pedidos = pedidoUsuarioService.listarTodos().stream()
                .map(PedidoUsuarioDTO::new)
                .toList();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoUsuarioDTO> buscarPorId(@PathVariable Long id) {
        PedidoUsuario pedido = pedidoUsuarioService.buscarPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não existir
        }
        return ResponseEntity.ok(new PedidoUsuarioDTO(pedido));
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PedidoUsuarioDTO>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<PedidoUsuarioDTO> pedidos = pedidoUsuarioService.buscarPorUsuario(idUsuario).stream()
                .map(PedidoUsuarioDTO::new)
                .toList();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoUsuarioDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido novoStatus) {
        PedidoUsuario pedidoAtualizado = pedidoUsuarioService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(new PedidoUsuarioDTO(pedidoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoUsuarioService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
