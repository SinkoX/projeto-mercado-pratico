package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/{idUsuario}/criar")
    public ResponseEntity<PedidoUsuarioDTO> criarPedido(@PathVariable Long idUsuario) {
        PedidoUsuario pedido = pedidoUsuarioService.criarPedido(idUsuario);
        return ResponseEntity.ok(new PedidoUsuarioDTO(pedido));
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
        return ResponseEntity.ok(new PedidoUsuarioDTO(pedido));
    }
    
    // listar pedidos por usuário
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PedidoUsuarioDTO>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<PedidoUsuarioDTO> pedidos = pedidoUsuarioService.buscarPorUsuario(idUsuario).stream()
                .map(PedidoUsuarioDTO::new)
                .toList();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoUsuarioDTO> atualizarStatus(@PathVariable Long id,
                                                            @RequestParam StatusPedido novoStatus) {
        PedidoUsuario pedidoAtualizado = pedidoUsuarioService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(new PedidoUsuarioDTO(pedidoAtualizado));
    }
}
