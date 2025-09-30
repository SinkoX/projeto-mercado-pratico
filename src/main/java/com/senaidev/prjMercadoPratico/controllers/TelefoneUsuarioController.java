package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.entities.TelefoneUsuario;
import com.senaidev.prjMercadoPratico.services.TelefoneUsuarioService;

@RestController
@RequestMapping("/telefonesUsuarios")
public class TelefoneUsuarioController {

    @Autowired
    private TelefoneUsuarioService telefoneUsuarioService;

    @GetMapping
    public ResponseEntity<List<TelefoneUsuario>> findAll() {
        return ResponseEntity.ok(telefoneUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneUsuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneUsuarioService.findById(id));
    }

    // Corrigido: retorna lista de telefones pelo id do usuário
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<TelefoneUsuario>> findByUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(telefoneUsuarioService.findByUsuario(idUsuario));
    }

    @GetMapping("/numero")
    public ResponseEntity<List<TelefoneUsuario>> findByNumeroTelefoneUsuario(@RequestParam("value") String numeroTelefoneUsuario) {
        return ResponseEntity.ok(telefoneUsuarioService.findByNumeroTelefoneUsuario(numeroTelefoneUsuario));
    }

    @GetMapping("/numero-ignore-case")
    public ResponseEntity<List<TelefoneUsuario>> findByNumeroTelefoneUsuarioIgnoreCase(@RequestParam("value") String numeroTelefoneUsuario) {
        return ResponseEntity.ok(telefoneUsuarioService.findByNumeroTelefoneUsuarioIgnoreCase(numeroTelefoneUsuario));
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existsByNumeroTelefoneUsuario(@RequestParam("numero") String numeroTelefoneUsuario) {
        return ResponseEntity.ok(telefoneUsuarioService.existsByNumeroTelefoneUsuario(numeroTelefoneUsuario));
    }

    @PostMapping
    public ResponseEntity<TelefoneUsuario> insert(@RequestBody TelefoneUsuario telefone) {
        return ResponseEntity.ok(telefoneUsuarioService.insert(telefone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneUsuario> update(@PathVariable Long id, @RequestBody TelefoneUsuario novoTelefone) {
        return ResponseEntity.ok(telefoneUsuarioService.update(id, novoTelefone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        telefoneUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
