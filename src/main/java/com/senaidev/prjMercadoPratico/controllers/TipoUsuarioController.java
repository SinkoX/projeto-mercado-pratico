package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.TipoUsuario;
import com.senaidev.prjMercadoPratico.services.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    // GET /tipos-usuario
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> findAll() {
        return ResponseEntity.ok(tipoUsuarioService.findAll());
    }

    // GET /tipos-usuario/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tipoUsuarioService.findById(id));
    }

    // GET /tipos-usuario/nome?value=...
    @GetMapping("/nome")
    public ResponseEntity<Optional<TipoUsuario>> findByNome(@RequestParam("value") String nome) {
        return ResponseEntity.ok(tipoUsuarioService.findByNome(nome));
    }

    // POST /tipos-usuario
    @PostMapping
    public ResponseEntity<TipoUsuario> insert(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario novoTipo = tipoUsuarioService.insert(tipoUsuario);
        return ResponseEntity.ok(novoTipo);
    }

    // PUT /tipos-usuario/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> update(@PathVariable Long id, @RequestBody TipoUsuario novoTipo) {
        TipoUsuario atualizado = tipoUsuarioService.update(id, novoTipo);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /tipos-usuario/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoUsuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
