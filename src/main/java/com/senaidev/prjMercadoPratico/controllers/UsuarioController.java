package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET /usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    // GET /usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    // POST /usuarios
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        Usuario novo = usuarioService.insert(usuario);
        return ResponseEntity.ok(novo);
    }

    // PUT /usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario atualizado = usuarioService.update(id, usuarioAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /usuarios/email?value=example@email.com
    @GetMapping("/email")
    public ResponseEntity<Usuario> findByEmail(@RequestParam("value") String email) {
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // GET /usuarios/cpf?value=12345678900
    @GetMapping("/cpf")
    public ResponseEntity<Usuario> findByCpf(@RequestParam("value") String cpf) {
        Usuario usuario = usuarioService.findByCpf(cpf);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    
    
}
