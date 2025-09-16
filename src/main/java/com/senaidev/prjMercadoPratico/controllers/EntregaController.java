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
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.Entrega;
import com.senaidev.prjMercadoPratico.services.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    // GET /entregas
    @GetMapping
    public ResponseEntity<List<Entrega>> findAll() {
        List<Entrega> entregas = entregaService.findAll();
        return ResponseEntity.ok(entregas);
    }

    // GET /entregas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> findById(@PathVariable Long id) {
        Entrega entrega = entregaService.findById(id);
        return ResponseEntity.ok(entrega);
    }

    // POST /entregas
    @PostMapping
    public ResponseEntity<Entrega> insert(@RequestBody Entrega entrega) {
        Entrega novaEntrega = entregaService.insert(entrega);
        return ResponseEntity.ok(novaEntrega);
    }

    // PUT /entregas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Entrega> update(@PathVariable Long id, @RequestBody Entrega entregaAtualizada) {
        Entrega entrega = entregaService.update(id, entregaAtualizada);
        return ResponseEntity.ok(entrega);
    }

    // DELETE /entregas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entregaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
