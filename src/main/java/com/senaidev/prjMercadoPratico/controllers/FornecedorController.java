package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.Fornecedor;
import com.senaidev.prjMercadoPratico.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    // GET /fornecedores
    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        List<Fornecedor> lista = fornecedorService.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET /fornecedores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.findById(id);
        return ResponseEntity.ok(fornecedor);
    }

    // GET /fornecedores/nome?exato=xxx
    @GetMapping("/nome")
    public ResponseEntity<List<Fornecedor>> findByNome(
            @RequestParam(required = false) String exato,
            @RequestParam(required = false) String contendo
    ) {
        if (exato != null) {
            return ResponseEntity.ok(fornecedorService.findByNomeExato(exato));
        } else if (contendo != null) {
            return ResponseEntity.ok(fornecedorService.findByNomeContendo(contendo));
        }
        return ResponseEntity.badRequest().build();
    }

    // GET /fornecedores/email?valor=xxx
    @GetMapping("/email")
    public ResponseEntity<Fornecedor> findByEmail(@RequestParam String valor) {
        Optional<Fornecedor> fornecedor = fornecedorService.findByEmail(valor);
        return fornecedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET /fornecedores/existe-cpf?cpf=xxx
    @GetMapping("/existe-cpf")
    public ResponseEntity<Boolean> existsByCpf(@RequestParam String cpf) {
        boolean exists = fornecedorService.existsByCpf(cpf);
        return ResponseEntity.ok(exists);
    }

    // GET /fornecedores/existe-cnpj?cnpj=xxx
    @GetMapping("/existe-cnpj")
    public ResponseEntity<Boolean> existsByCnpj(@RequestParam String cnpj) {
        boolean exists = fornecedorService.existsByCnpj(cnpj);
        return ResponseEntity.ok(exists);
    }

    // GET /fornecedores/cpf-ou-cnpj?cpf=xxx&cnpj=yyy
    @GetMapping("/cpf-ou-cnpj")
    public ResponseEntity<Fornecedor> findByCpfOrCnpj(@RequestParam String cpf, @RequestParam String cnpj) {
        Optional<Fornecedor> fornecedor = fornecedorService.findByCpfOrCnpj(cpf, cnpj);
        return fornecedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /fornecedores
    @PostMapping("/cadastro")
    public ResponseEntity<Fornecedor> insert(@RequestBody Fornecedor fornecedor) {
        Fornecedor novo = fornecedorService.insert(fornecedor);
        return ResponseEntity.ok(novo);
    }

    // PUT /fornecedores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody Fornecedor atualizado) {
        Fornecedor fornecedor = fornecedorService.update(id, atualizado);
        return ResponseEntity.ok(fornecedor);
    }

    // DELETE /fornecedores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
