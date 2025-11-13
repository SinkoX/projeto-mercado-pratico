package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.services.ResetTokenService;

@RestController
@RequestMapping("/usuario")
public class ResetTokenController {

    @Autowired
    private ResetTokenService resetTokenService;

    // Solicitar código
    @PostMapping("/solicitar-reset")
    public ResponseEntity<String> solicitarReset(@RequestParam String email) {
        try {
            resetTokenService.solicitarRedefinicao(email);
            return ResponseEntity.ok("Código enviado para o e-mail");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Redefinir senha
    @PostMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@RequestParam String codigo,
                                                 @RequestParam String novaSenha) {
        try {
            resetTokenService.redefinirSenha(codigo, novaSenha);
            return ResponseEntity.ok("Senha redefinida com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
