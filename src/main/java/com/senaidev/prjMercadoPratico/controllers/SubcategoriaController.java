package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.repositories.SubcategoriaRepository;

@RestController
@RequestMapping("/subcategorias")
@CrossOrigin(origins = "http://localhost:5173")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todas as subcategorias
    @GetMapping
    public List<Subcategoria> listarTodas() {
        return subcategoriaRepository.findAll();
    }

    // Buscar produtos por subcategoria
    @GetMapping("/{nomeSubcategoria}/produtos")
    public List<Produto> listarProdutosPorSubcategoria(@PathVariable String nomeSubcategoria) {
        Subcategoria subcategoria = subcategoriaRepository.findByNomeSubcategoriaIgnoreCase(nomeSubcategoria)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada"));

        return produtoRepository.findBySubcategoria(subcategoria);
    }
}
