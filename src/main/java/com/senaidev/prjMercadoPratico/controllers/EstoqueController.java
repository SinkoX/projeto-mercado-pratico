package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.EstoqueDTO;
import com.senaidev.prjMercadoPratico.entities.Estoque;
import com.senaidev.prjMercadoPratico.services.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/{idProduto}")
    public ResponseEntity<EstoqueDTO> criarEstoque(@PathVariable Long idProduto,
                                                @RequestParam Integer quantidade) {
        Estoque estoque = service.criarEstoque(idProduto, quantidade);
        return ResponseEntity.ok(new EstoqueDTO(estoque));
    }

    @PutMapping("/entrada/{idProduto}")
    public ResponseEntity<EstoqueDTO> registrarEntrada(@PathVariable Long idProduto,
                                                    @RequestParam Integer quantidade) {
        Estoque estoque = service.registrarEntrada(idProduto, quantidade);
        return ResponseEntity.ok(new EstoqueDTO(estoque));
    }

    @PutMapping("/saida/{idProduto}")
    public ResponseEntity<EstoqueDTO> registrarSaida(@PathVariable Long idProduto,
                                                  @RequestParam Integer quantidade) {
        Estoque estoque = service.registrarSaida(idProduto, quantidade);
        return ResponseEntity.ok(new EstoqueDTO(estoque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
