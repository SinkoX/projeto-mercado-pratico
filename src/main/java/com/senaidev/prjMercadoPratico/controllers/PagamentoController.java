package com.senaidev.prjMercadoPratico.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.services.PedidoUsuarioService;
@Controller
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PedidoUsuarioService pedidoUsuarioService;

    public PagamentoController(PedidoUsuarioService pedidoUsuarioService) {
        this.pedidoUsuarioService = pedidoUsuarioService;
    }

    @PostMapping("/finalizar/{idUsuario}")
    public ResponseEntity<?> finalizarPagamento(@PathVariable Long idUsuario) {
        try {
            String checkoutUrl = pedidoUsuarioService.criarPedidoComStripe(idUsuario);
            return ResponseEntity.ok(Map.of("checkoutUrl", checkoutUrl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
