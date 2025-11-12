package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.services.ProdutoService;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:5173")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
    @Autowired
    private ProdutoRepository produtoRepository;

    // Buscar todos os produtos
    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.findAllDTO();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarPorId(@PathVariable Long id) {
        return produtoService.findByIdDTO(id);
    }

    // Buscar produtos pelo nome (parcial ou completo)
    @GetMapping("/busca")
    public List<Produto> buscarPorNome(@RequestParam String nome) {
        return produtoRepository.findByNomeProdutoContainingIgnoreCase(nome);
    }
    
    @PostMapping("/cadastro")
    public ResponseEntity<ProdutoDTO> insertDTO(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO novoProduto = produtoService.insertDTO(produtoDTO);
        return ResponseEntity.ok(novoProduto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateDTO(@PathVariable Long id, @RequestBody ProdutoDTO produtoAtualizado) {
        ProdutoDTO atualizado = produtoService.updateDTO(id, produtoAtualizado);
        return ResponseEntity.ok(atualizado);
    }
}
