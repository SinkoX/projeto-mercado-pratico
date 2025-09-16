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

import com.senaidev.prjMercadoPratico.entities.TelefoneFornecedor;
import com.senaidev.prjMercadoPratico.services.TelefoneFornecedorService;

@RestController
@RequestMapping("/telefones-fornecedores")
public class TelefoneFornecedorController {

    @Autowired
    private TelefoneFornecedorService telefoneFornecedorService;

    // GET /telefones-fornecedores
    @GetMapping
    public ResponseEntity<List<TelefoneFornecedor>> findAll() {
        return ResponseEntity.ok(telefoneFornecedorService.findAll());
    }

    // GET /telefones-fornecedores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TelefoneFornecedor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneFornecedorService.findById(id));
    }

    // GET /telefones-fornecedores/fornecedor/{idFornecedor}
    @GetMapping("/fornecedor/{idFornecedor}")
    public ResponseEntity<List<TelefoneFornecedor>> findByFornecedor(@PathVariable Long idFornecedor) {
        return ResponseEntity.ok(telefoneFornecedorService.findByFornecedorId(idFornecedor));
    }

    // GET /telefones-fornecedores/numero?value=11999999999
    @GetMapping("/numero")
    public ResponseEntity<List<TelefoneFornecedor>> findByNumero(@RequestParam("value") String numero) {
        return ResponseEntity.ok(telefoneFornecedorService.findByNumeroTelefone(numero));
    }

    // GET /telefones-fornecedores/numero-ignore-case?value=11999999999
    @GetMapping("/numero-ignore-case")
    public ResponseEntity<List<TelefoneFornecedor>> findByNumeroIgnoreCase(@RequestParam("value") String numero) {
        return ResponseEntity.ok(telefoneFornecedorService.findByNumeroTelefoneIgnoreCase(numero));
    }

    // POST /telefones-fornecedores
    @PostMapping
    public ResponseEntity<TelefoneFornecedor> insert(@RequestBody TelefoneFornecedor telefone) {
        return ResponseEntity.ok(telefoneFornecedorService.insert(telefone));
    }

    // PUT /telefones-fornecedores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneFornecedor> update(@PathVariable Long id, @RequestBody TelefoneFornecedor novoTelefone) {
        return ResponseEntity.ok(telefoneFornecedorService.update(id, novoTelefone));
    }

    // DELETE /telefones-fornecedores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        telefoneFornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
