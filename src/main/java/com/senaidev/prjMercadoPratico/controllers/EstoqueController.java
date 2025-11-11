package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.EstoqueDTO;
import com.senaidev.prjMercadoPratico.services.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    // 🔹 Criar novo estoque via JSON
    @PostMapping
    public ResponseEntity<EstoqueDTO> criar(@RequestBody EstoqueDTO dto) {
        EstoqueDTO novo = estoqueService.criar(dto);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.buscarPorId(id));
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<EstoqueDTO> buscarPorProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(estoqueService.buscarPorProduto(idProduto));
    }

    @PutMapping("/{idEstoque}/minimo")
    public ResponseEntity<EstoqueDTO> atualizarQuantidadeMinima(
            @PathVariable Long idEstoque,
            @RequestParam Integer novaQuantidadeMinima) {

        EstoqueDTO dto = estoqueService.atualizarQuantidadeMinima(idEstoque, novaQuantidadeMinima);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idEstoque}/adicionar")
    public ResponseEntity<EstoqueDTO> adicionarQuantidade(
            @PathVariable Long idEstoque,
            @RequestParam Integer quantidade) {

        EstoqueDTO dto = estoqueService.adicionarQuantidade(idEstoque, quantidade);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idEstoque}/remover")
    public ResponseEntity<EstoqueDTO> removerQuantidade(
            @PathVariable Long idEstoque,
            @RequestParam Integer quantidade) {

        EstoqueDTO dto = estoqueService.removerQuantidade(idEstoque, quantidade);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/abaixo-minimo")
    public ResponseEntity<List<EstoqueDTO>> buscarAbaixoMinimo() {
        return ResponseEntity.ok(estoqueService.buscarEstoqueAbaixoDoMinimo());
    }

    @GetMapping("/zerado")
    public ResponseEntity<List<EstoqueDTO>> buscarZerados() {
        return ResponseEntity.ok(estoqueService.buscarEstoqueZerado());
    }

    @DeleteMapping("/{idEstoque}")
    public ResponseEntity<Void> deletar(@PathVariable Long idEstoque) {
        estoqueService.deletar(idEstoque);
        return ResponseEntity.noContent().build();
    }
}
