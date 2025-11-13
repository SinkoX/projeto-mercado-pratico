package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.entities.Fornecimento;
import com.senaidev.prjMercadoPratico.services.FornecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fornecimentos")
public class FornecimentoController {

    @Autowired
    private FornecimentoService fornecimentoService;

    @GetMapping
    public List<Fornecimento> findAll() {
        return fornecimentoService.findAll();
    }

    @GetMapping("/{id}")
    public Fornecimento findById(@PathVariable Long id) {
        return fornecimentoService.findById(id);
    }

    @PostMapping
    public Fornecimento insert(@RequestBody Fornecimento fornecimento) {
        return fornecimentoService.insert(fornecimento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fornecimentoService.delete(id);
    }
}
