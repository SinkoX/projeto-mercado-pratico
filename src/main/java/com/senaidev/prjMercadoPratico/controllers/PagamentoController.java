package com.senaidev.prjMercadoPratico.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.dto.PagamentoDTO;
import com.senaidev.prjMercadoPratico.entities.Endereco;
import com.senaidev.prjMercadoPratico.services.PedidoUsuarioService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PedidoUsuarioService pedidoUsuarioService;

    public PagamentoController(PedidoUsuarioService pedidoUsuarioService) {
        this.pedidoUsuarioService = pedidoUsuarioService;
    }

    @PostMapping("/finalizar/{idUsuario}")
    public ResponseEntity<?> finalizarPagamento(
            @PathVariable Long idUsuario,
            @RequestBody PagamentoDTO pagamentoDTO) {
        try {
            // Monta o endereço a partir do DTO
            Endereco endereco = new Endereco();
            endereco.setId_endereco(pagamentoDTO.getIdEnderecoEntrega());
            // Se quiser, pode setar rua, número, bairro, cidade, cep também

            // Chama o service já existente
            String checkoutUrl = pedidoUsuarioService.criarPedidoComStripe(
                    idUsuario,
                    endereco,
                    pagamentoDTO.getFrete() != null ? pagamentoDTO.getFrete() : BigDecimal.ZERO,
                    pagamentoDTO.getDesconto() != null ? pagamentoDTO.getDesconto() : BigDecimal.ZERO
            );

            return ResponseEntity.ok().body("{\"checkoutUrl\": \"" + checkoutUrl + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("{\"erro\": \"" + e.getMessage() + "\"}");
        }
    }
}
