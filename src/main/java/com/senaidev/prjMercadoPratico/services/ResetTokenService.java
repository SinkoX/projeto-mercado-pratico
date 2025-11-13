package com.senaidev.prjMercadoPratico.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.ResetToken;
import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.ResetTokenRepository;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;

@Service
public class ResetTokenService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Autowired
    private EmailService emailService; // envia códigos

    private Random rand = new Random();

    // Solicitar código
    public void solicitarRedefinicao(String email) throws Exception {
        Usuario usuario = usuarioRepository.findByEmailUsuario(email);
        if (usuario == null) throw new Exception("Usuário não encontrado");

        String codigo = gerarCodigo();
        LocalDateTime expiracao = LocalDateTime.now().plusMinutes(15);

        ResetToken token = new ResetToken();
        token.setCodigo(codigo);
        token.setExpiracao(expiracao);
        token.setUsuario(usuario);
        token.setUsado(false);

        resetTokenRepository.save(token);
        emailService.enviarCodigo(email, codigo);
    }

    // Redefinir senha
    public void redefinirSenha(String codigo, String novaSenha) throws Exception {
        ResetToken token = resetTokenRepository.findByCodigoAndUsadoFalse(codigo)
                .orElseThrow(() -> new Exception("Código inválido ou já usado"));

        if (token.getExpiracao().isBefore(LocalDateTime.now())) {
            throw new Exception("Código expirado");
        }

        Usuario usuario = token.getUsuario();
        usuario.setSenhaUsuario(new BCryptPasswordEncoder().encode(novaSenha));
        usuarioRepository.save(usuario);

        token.setUsado(true);
        resetTokenRepository.save(token);
    }

    private String gerarCodigo() {
        int codigo = 100000 + rand.nextInt(900000);
        return String.valueOf(codigo);
    }
}
