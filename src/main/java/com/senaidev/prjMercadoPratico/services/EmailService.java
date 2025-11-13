package com.senaidev.prjMercadoPratico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envia o código de redefinição de senha para o email do usuário.
     * Funciona com FakeSMTP local.
     */
    public void enviarCodigo(String para, String codigo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(para);
        message.setSubject("Redefinição de senha - PrjMercadoPratico");
        message.setText("Olá,\n\nSeu código para redefinir a senha é: " + codigo +
                "\n\nEste código expira em 15 minutos. Se você não solicitou, ignore esta mensagem.");

        // Envia o email
        mailSender.send(message);

        // Apenas para debug em console
        System.out.println("Email enviado (simulado) para: " + para + " com código: " + codigo);
    }
}
