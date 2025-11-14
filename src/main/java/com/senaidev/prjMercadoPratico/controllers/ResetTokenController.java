package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.dto.RedefinirSenhaDTO;
import com.senaidev.prjMercadoPratico.dto.SolicitarResetDTO;
import com.senaidev.prjMercadoPratico.services.ResetTokenService;

@RestController
@RequestMapping("/redef")
public class ResetTokenController {

    @Autowired
    private ResetTokenService resetTokenService;

    // Solicitar código via JSON
    @PostMapping("/solicitar-reset")
    public ResponseEntity<String> solicitarReset(@RequestBody SolicitarResetDTO dto) {
        try {
            resetTokenService.solicitarRedefinicao(dto.getEmail());
            return ResponseEntity.ok("Código enviado para o e-mail");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Redefinir senha via JSON
    @PostMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@RequestBody RedefinirSenhaDTO dto) {
        try {
            resetTokenService.redefinirSenha(dto.getCodigo(), dto.getNovaSenha());
            return ResponseEntity.ok("Senha redefinida com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
