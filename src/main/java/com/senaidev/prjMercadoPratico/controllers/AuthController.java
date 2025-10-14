package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String senha = body.get("senha");

        Usuario usuario = usuarioRepository.findByEmailUsuarioAndSenhaUsuario(email, senha);
        if (usuario == null) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        // Guarda o usuário na sessão
        session.setAttribute("usuarioLogado", usuario);

        // Retorna dados básicos do usuário
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuario-logado")
    public ResponseEntity<?> usuarioLogado(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return ResponseEntity.status(401).body("Não autenticado");
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout realizado");
    }
}
