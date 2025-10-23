package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todas as categorias
    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // Buscar produtos por categoria
    @GetMapping("/{nomeCategoria}/produtos")
    public List<Produto> listarProdutosPorCategoria(@PathVariable String nomeCategoria) {
        Categoria categoria = categoriaRepository.findByNomeCategoriaIgnoreCase(nomeCategoria)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return produtoRepository.findBySubcategoria_Categoria(categoria);
    }
}
