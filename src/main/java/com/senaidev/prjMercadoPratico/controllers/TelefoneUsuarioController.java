package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.TelefoneUsuario;
import com.senaidev.prjMercadoPratico.services.TelefoneUsuarioService;

@RestController
@RequestMapping("/telefonesUsuarios")
public class TelefoneUsuarioController {

    @Autowired
    private TelefoneUsuarioService telefoneUsuarioService;

    // GET /telefones-clientes
    @GetMapping
    public ResponseEntity<List<TelefoneUsuario>> findAll() {
        return ResponseEntity.ok(telefoneUsuarioService.findAll());
    }

    // GET /telefones-clientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TelefoneUsuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneUsuarioService.findById(id));
    }

    // GET /telefones-clientes/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<TelefoneUsuario>> findByUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(telefoneUsuarioService.findByUsuario(idUsuario));
    }

    // GET /telefones-clientes/numero?value=11999999999
    @GetMapping("/numero")
    public ResponseEntity<List<TelefoneUsuario>> findByNumero(@RequestParam("value") String numero) {
        return ResponseEntity.ok(telefoneUsuarioService.findByNumeroTelefone(numero));
    }

    // GET /telefones-clientes/numero-ignore-case?value=11999999999
    @GetMapping("/numero-ignore-case")
    public ResponseEntity<List<TelefoneUsuario>> findByNumeroIgnoreCase(@RequestParam("value") String numero) {
        return ResponseEntity.ok(telefoneUsuarioService.findByNumeroTelefoneIgnoreCase(numero));
    }

    // GET /telefones-clientes/existe?numero=11999999999
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existsByNumeroTelefone(@RequestParam("numero") String numero) {
        return ResponseEntity.ok(telefoneUsuarioService.existsByNumeroTelefone(numero));
    }

    // POST /telefones-clientes
    @PostMapping
    public ResponseEntity<TelefoneUsuario> insert(@RequestBody TelefoneUsuario telefone) {
        return ResponseEntity.ok(telefoneUsuarioService.insert(telefone));
    }

    // PUT /telefones-clientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneUsuario> update(@PathVariable Long id, @RequestBody TelefoneUsuario novoTelefone) {
        return ResponseEntity.ok(telefoneUsuarioService.update(id, novoTelefone));
    }

    // DELETE /telefones-clientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        telefoneUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
