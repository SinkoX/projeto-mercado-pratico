package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.services.CategoriaService;
import com.senaidev.prjMercadoPratico.services.ProdutoService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    // Listar todas as categorias
    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    // Buscar produtos por categoria (retorna DTO para evitar loops infinitos)
    @GetMapping("/nome/{nomeCategoria}/produtos")
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

    // Buscar subcategorias por nome
    @GetMapping("/nome/{nomeCategoria}")
    public List<Subcategoria> listarSubcategorias(@PathVariable String nomeCategoria) {
        Categoria categoria = categoriaRepository.findByNomeCategoriaIgnoreCase(nomeCategoria)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoria.getSubcategorias();
    }

    // Buscar subcategorias por ID
    @GetMapping("/id/{id}")
    public List<Subcategoria> listarSubcategoriasById(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoria.getSubcategorias();
    }

    // ✅ POST - Criar nova categoria
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria nova = categoriaService.save(categoria);
        return ResponseEntity.ok(nova);
    }

    // ✅ PUT - Atualizar categoria existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
        Categoria atualizada = categoriaService.update(id, categoriaAtualizada);
        return ResponseEntity.ok(atualizada);
    }
    
    // DELETE - Deletar subcategoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
