package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.services.SubcategoriaService;

@RestController
@RequestMapping("/subcategorias")
@CrossOrigin(origins = "http://localhost:5173")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private ProdutoRepository produtoRepository;

    // GET - Listar todas as subcategorias
    @GetMapping
    public List<Subcategoria> listarTodas() {
        return subcategoriaService.findAll();
    }

    // GET - Buscar produtos por nome da subcategoria
    @GetMapping("/{nomeSubcategoria}/produtos")
    public List<Produto> listarProdutosPorSubcategoria(@PathVariable String nomeSubcategoria) {
        Subcategoria subcategoria = subcategoriaService.findByNomeSubcategoriaIgnoreCase(nomeSubcategoria)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategoria não encontrada"));
        return produtoRepository.findBySubcategoria(subcategoria);
    }

    // POST - Criar nova subcategoria
    @PostMapping
    public ResponseEntity<Subcategoria> criarSubcategoria(@RequestBody Subcategoria subcategoria) {
        Subcategoria nova = subcategoriaService.save(subcategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    // PUT - Atualizar subcategoria existente
    @PutMapping("/{id}")
    public ResponseEntity<Subcategoria> atualizarSubcategoria(
            @PathVariable Long id,
            @RequestBody Subcategoria novaSubcategoria) {

        Subcategoria atualizada = subcategoriaService.update(id, novaSubcategoria);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE - Deletar subcategoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSubcategoria(@PathVariable Long id) {
        subcategoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
