package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testeBCryptController {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Endpoint para gerar hash e testar se bate com a senha original
    // Exemplo de acesso: GET http://localhost:8080/teste-bcrypt?senha=minhaSenha123
    @GetMapping("/teste-bcrypt")
    public String testeBCrypt(@RequestParam("senha") String senha) {
        // Gera o hash
        String hash = encoder.encode(senha);
        
        // Verifica se bate com a senha original
        boolean matches = encoder.matches(senha, hash);
        
        return "Senha original: " + senha + 
               "\nHash gerado: " + hash + 
               "\nSenha bate com hash? " + matches;
    }
}
