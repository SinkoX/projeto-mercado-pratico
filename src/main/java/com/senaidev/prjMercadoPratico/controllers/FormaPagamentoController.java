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

import com.senaidev.prjMercadoPratico.entities.FormaPagamento;
import com.senaidev.prjMercadoPratico.services.FormaPagamentoService;

@RestController
@RequestMapping("/formaspagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    // GET /formas-pagamento
    @GetMapping
    public ResponseEntity<List<FormaPagamento>> findAll() {
        List<FormaPagamento> lista = formaPagamentoService.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET /formas-pagamento/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> findById(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.findById(id);
        return ResponseEntity.ok(formaPagamento);
    }

    // POST /formas-pagamento
    @PostMapping
    public ResponseEntity<FormaPagamento> insert(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento nova = formaPagamentoService.insert(formaPagamento);
        return ResponseEntity.ok(nova);
    }

    // PUT /formas-pagamento/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> update(@PathVariable Long id, @RequestBody FormaPagamento formaAtualizada) {
        FormaPagamento formaPagamento = formaPagamentoService.update(id, formaAtualizada);
        return ResponseEntity.ok(formaPagamento);
    }

    // DELETE /formas-pagamento/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formaPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
