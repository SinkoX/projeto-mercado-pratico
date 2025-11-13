package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.services.EmailService;

@RestController
public class TestEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/teste-email")
    public String testeEmail() {
        emailService.enviarCodigo("julio.botaccio@gmail.com", "123456");
        return "Email enviado! Verifique no FakeSMTP GUI";
    }
}
