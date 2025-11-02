package com.senaidev.prjMercadoPratico.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.dto.PagamentoDTO;
import com.senaidev.prjMercadoPratico.entities.Endereco;
import com.senaidev.prjMercadoPratico.services.EnderecoService;
import com.senaidev.prjMercadoPratico.services.PedidoUsuarioService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PedidoUsuarioService pedidoUsuarioService;
    private final EnderecoService enderecoService;

    public PagamentoController(PedidoUsuarioService pedidoUsuarioService,
                               EnderecoService enderecoService) {
        this.pedidoUsuarioService = pedidoUsuarioService;
        this.enderecoService = enderecoService;
    }

    @PostMapping("/finalizar/{idUsuario}")
    public ResponseEntity<?> finalizarPagamento(
            @PathVariable Long idUsuario,
            @RequestBody PagamentoDTO pagamentoRequest) {

        try {
            Endereco enderecoEntrega = enderecoService.buscarPorId(pagamentoRequest.getIdEnderecoEntrega());
            String checkoutUrl = pedidoUsuarioService.criarPedidoComStripe(
                    idUsuario,
                    enderecoEntrega,
                    pagamentoRequest.getFrete(),
                    pagamentoRequest.getDesconto()
            );

            return ResponseEntity.ok(Map.of("checkoutUrl", checkoutUrl));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
