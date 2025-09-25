package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;
import java.util.Optional;

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

import com.senaidev.prjMercadoPratico.entities.Funcionario;
import com.senaidev.prjMercadoPratico.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // GET /funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        return ResponseEntity.ok(funcionarioService.findAll());
    }

    // GET /funcionarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        return funcionario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // GET /funcionarios/nome?valor=xxx
    @GetMapping("/nome")
    public ResponseEntity<List<Funcionario>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(funcionarioService.findByNome(nome));
    }
    
 // GET /funcionarios/cpf?valor=xxx
    @GetMapping("/cpf")
    public ResponseEntity<List<Funcionario>> findByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(funcionarioService.findByCpfFuncionario(cpf));
    }

    // GET /funcionarios/cargo?valor=xxx
    @GetMapping("/cargo")
    public ResponseEntity<List<Funcionario>> findByCargo(@RequestParam String cargo) {
        return ResponseEntity.ok(funcionarioService.findByCargo(cargo));
    }

    // GET /funcionarios/email-existe?email=xxx@yyy.com
    @GetMapping("/email-existe")
    public ResponseEntity<Boolean> emailExiste(@RequestParam String email) {
        return ResponseEntity.ok(funcionarioService.emailExiste(email));
    }

    // POST /funcionarios
    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.save(funcionario);
        return ResponseEntity.ok(novoFuncionario);
    }

    // PUT /funcionarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        Funcionario atualizado = funcionarioService.update(id, funcionarioAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /funcionarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
