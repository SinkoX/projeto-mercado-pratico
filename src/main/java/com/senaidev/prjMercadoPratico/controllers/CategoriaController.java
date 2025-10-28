package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.services.ProdutoService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    // Listar todas as categorias
    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // Buscar produtos por categoria (retorna DTO para evitar loops infinitos)
    @GetMapping("/{nomeCategoria}/produtos")
    public List<ProdutoDTO> listarProdutosPorCategoria(@PathVariable String nomeCategoria) {
        String nome = nomeCategoria.trim().toLowerCase();

        Categoria categoria = categoriaRepository.findAll().stream()
                .filter(c -> c.getNomeCategoria().trim().toLowerCase().equals(nome))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        List<Produto> produtos = produtoRepository.findBySubcategoria_Categoria(categoria);
        return produtos.stream()
                .map(produtoService::toDTO)
                .toList();
    }
    
    @GetMapping("/{nomeCategoria}")
    public List<Subcategoria> listarSubcategorias(@PathVariable String nomeCategoria) {
        Categoria categoria = categoriaRepository.findByNomeCategoriaIgnoreCase(nomeCategoria)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoria.getSubcategorias();
    }
}
