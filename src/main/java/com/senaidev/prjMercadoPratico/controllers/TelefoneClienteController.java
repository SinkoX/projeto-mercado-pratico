package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.TelefoneCliente;
import com.senaidev.prjMercadoPratico.services.TelefoneClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones-clientes")
public class TelefoneClienteController {

    @Autowired
    private TelefoneClienteService telefoneClienteService;

    // GET /telefones-clientes
    @GetMapping
    public ResponseEntity<List<TelefoneCliente>> findAll() {
        return ResponseEntity.ok(telefoneClienteService.findAll());
    }

    // GET /telefones-clientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TelefoneCliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneClienteService.findById(id));
    }

    // GET /telefones-clientes/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<TelefoneCliente>> findByUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(telefoneClienteService.findByUsuario(idUsuario));
    }

    // GET /telefones-clientes/numero?value=11999999999
    @GetMapping("/numero")
    public ResponseEntity<List<TelefoneCliente>> findByNumero(@RequestParam("value") String numero) {
        return ResponseEntity.ok(telefoneClienteService.findByNumeroTelefone(numero));
    }

    // GET /telefones-clientes/numero-ignore-case?value=11999999999
    @GetMapping("/numero-ignore-case")
    public ResponseEntity<List<TelefoneCliente>> findByNumeroIgnoreCase(@RequestParam("value") String numero) {
        return ResponseEntity.ok(telefoneClienteService.findByNumeroTelefoneIgnoreCase(numero));
    }

    // GET /telefones-clientes/existe?numero=11999999999
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existsByNumeroTelefone(@RequestParam("numero") String numero) {
        return ResponseEntity.ok(telefoneClienteService.existsByNumeroTelefone(numero));
    }

    // POST /telefones-clientes
    @PostMapping
    public ResponseEntity<TelefoneCliente> insert(@RequestBody TelefoneCliente telefone) {
        return ResponseEntity.ok(telefoneClienteService.insert(telefone));
    }

    // PUT /telefones-clientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneCliente> update(@PathVariable Long id, @RequestBody TelefoneCliente novoTelefone) {
        return ResponseEntity.ok(telefoneClienteService.update(id, novoTelefone));
    }

    // DELETE /telefones-clientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        telefoneClienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
