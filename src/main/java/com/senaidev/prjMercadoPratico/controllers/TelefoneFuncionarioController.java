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

import com.senaidev.prjMercadoPratico.entities.TelefoneFuncionario;
import com.senaidev.prjMercadoPratico.services.TelefoneFuncionarioService;

@RestController
@RequestMapping("/telefones-funcionarios")
public class TelefoneFuncionarioController {

    @Autowired
    private TelefoneFuncionarioService telefoneFuncionarioService;

    // GET /telefones-funcionarios
    @GetMapping
    public ResponseEntity<List<TelefoneFuncionario>> findAll() {
        return ResponseEntity.ok(telefoneFuncionarioService.findAll());
    }

    // GET /telefones-funcionarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TelefoneFuncionario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneFuncionarioService.findById(id));
    }

    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<List<TelefoneFuncionario>> findByFuncionarioId(@PathVariable Long idFuncionario) {
        return ResponseEntity.ok(telefoneFuncionarioService.findByFuncionarioId(idFuncionario));
    }

    // GET /telefones-funcionarios/numero?value=...
    @GetMapping("/numero")
    public ResponseEntity<List<TelefoneFuncionario>> findByNumeroFuncionario(@RequestParam("value") String numeroTelefoneFuncionario) {
        return ResponseEntity.ok(telefoneFuncionarioService.findByNumeroTelefoneFuncionario(numeroTelefoneFuncionario));
    }

    // GET /telefones-funcionarios/numero-ignore-case?value=...
    @GetMapping("/numero-ignore-case")
    public ResponseEntity<List<TelefoneFuncionario>> findByNumeroFuncionarioIgnoreCase(@RequestParam("value") String numeroTelefoneFuncionario) {
        return ResponseEntity.ok(telefoneFuncionarioService.findByNumeroTelefoneFuncionarioIgnoreCase(numeroTelefoneFuncionario));
    }

    // POST /telefones-funcionarios
    @PostMapping
    public ResponseEntity<TelefoneFuncionario> insert(@RequestBody TelefoneFuncionario telefone) {
        return ResponseEntity.ok(telefoneFuncionarioService.insert(telefone));
    }

    // PUT /telefones-funcionarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneFuncionario> update(@PathVariable Long id, @RequestBody TelefoneFuncionario novoTelefone) {
        return ResponseEntity.ok(telefoneFuncionarioService.update(id, novoTelefone));
    }

    // DELETE /telefones-funcionarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        telefoneFuncionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
