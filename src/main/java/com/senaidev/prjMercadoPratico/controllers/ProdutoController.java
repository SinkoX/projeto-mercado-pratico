package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Buscar todos os produtos como DTO
    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.findAllDTO();
    }

    // Buscar produto por ID como DTO
    @GetMapping("/{id}")
    public ProdutoDTO buscarPorId(@PathVariable Long id) {
        return produtoService.findByIdDTO(id);
    }

    // Buscar produtos pelo nome (parcial ou completo) como DTO
    @GetMapping("/busca")
    public List<ProdutoDTO> buscarPorNome(@RequestParam String nome) {
        return produtoService.findAllDTO().stream()
                .filter(p -> p.getNomeProduto().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }
}
