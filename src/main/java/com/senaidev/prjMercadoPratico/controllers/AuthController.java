package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder; // Injetado como bean

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String senha = body.get("senha");

        // Busca usuário pelo email
        Usuario usuario = usuarioRepository.findByEmailUsuario(email);
        if (usuario == null) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        // Teste: imprime no console
        System.out.println("Senha enviada: " + senha);
        System.out.println("Hash do banco: " + usuario.getSenhaUsuario());

        // Verifica senha usando BCrypt
        boolean senhaBate = encoder.matches(senha, usuario.getSenhaUsuario());
        System.out.println("Senha bate? " + senhaBate);

        if (!senhaBate) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        // Salva usuário na sessão
        session.setAttribute("usuarioLogado", usuario);

        // Não expor a senha para o front
        usuario.setSenhaUsuario(null);

        return ResponseEntity.ok(usuario);
    }

    // Retorna usuário logado
    @GetMapping("/usuario-logado")
    public ResponseEntity<?> usuarioLogado(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return ResponseEntity.status(401).body("Não autenticado");
        }
        usuario.setSenhaUsuario(null);
        return ResponseEntity.ok(usuario);
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout realizado");
    }
}
