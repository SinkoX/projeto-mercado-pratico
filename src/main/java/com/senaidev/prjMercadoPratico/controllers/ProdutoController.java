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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController<produtoService> {

    @Autowired
    private ProdutoService produtoService;

    // GET /produtos
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    // GET /produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    // GET /produtos/nome?value=Arroz
    @GetMapping("/nome")
    public ResponseEntity<List<Produto>> findByNome(@RequestParam("value") String nome) {
        return ResponseEntity.ok(produtoService.findByNome(nome));
    }

    // GET /produtos/categoria?value=Hortifruti
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Produto>> findByCategoria(@PathVariable Long id) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(id);
        return ResponseEntity.ok(produtoService.findByCategoria(categoria));
    }


    // GET /produtos/vencidos
    @GetMapping("/vencidos")
    public ResponseEntity<List<Produto>> findProdutosVencidos() {
        return ResponseEntity.ok(produtoService.findProdutosVencidos());
    }

    // POST /produtos
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        Produto novo = produtoService.insert(produto);
        return ResponseEntity.ok(novo);
    }

    // PUT /produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto atualizado = produtoService.update(id, produtoAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
