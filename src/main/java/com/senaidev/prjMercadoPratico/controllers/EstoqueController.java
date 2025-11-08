package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.EstoqueDTO;
import com.senaidev.prjMercadoPratico.services.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }
    
 // 🔹 Criar estoque
    @PostMapping("/criar")
    public ResponseEntity<EstoqueDTO> criar(
            @RequestParam Long idProduto,
            @RequestParam Integer quantidadeInicial,
            @RequestParam Integer quantidadeMinima) {

        return ResponseEntity.ok(
                estoqueService.criar(idProduto, quantidadeInicial, quantidadeMinima)
        );
    }

    // 🔹 Listar todos
    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.buscarPorId(id));
    }

    // 🔹 Buscar por Produto
    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<EstoqueDTO> buscarPorProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(estoqueService.buscarPorProduto(idProduto));
    }

    

    // 🔹 Atualizar quantidade mínima
    @PutMapping("/{idEstoque}/minimo")
    public ResponseEntity<EstoqueDTO> atualizarQuantidadeMinima(
            @PathVariable Long idEstoque,
            @RequestParam Integer novaQuantidadeMinima) {

        return ResponseEntity.ok(
                estoqueService.atualizarQuantidadeMinima(idEstoque, novaQuantidadeMinima)
        );
    }

    // 🔹 Adicionar quantidade
    @PutMapping("/{idEstoque}/adicionar")
    public ResponseEntity<EstoqueDTO> adicionarQuantidade(
            @PathVariable Long idEstoque,
            @RequestParam Integer quantidade) {

        return ResponseEntity.ok(
                estoqueService.adicionarQuantidade(idEstoque, quantidade)
        );
    }

    // 🔹 Remover quantidade
    @PutMapping("/{idEstoque}/remover")
    public ResponseEntity<EstoqueDTO> removerQuantidade(
            @PathVariable Long idEstoque,
            @RequestParam Integer quantidade) {

        return ResponseEntity.ok(
                estoqueService.removerQuantidade(idEstoque, quantidade)
        );
    }

    // 🔹 Estoques abaixo do mínimo
    @GetMapping("/abaixo-minimo")
    public ResponseEntity<List<EstoqueDTO>> buscarAbaixoMinimo() {
        return ResponseEntity.ok(estoqueService.buscarEstoqueAbaixoDoMinimo());
    }

    // 🔹 Estoques zerados
    @GetMapping("/zerado")
    public ResponseEntity<List<EstoqueDTO>> buscarZerados() {
        return ResponseEntity.ok(estoqueService.buscarEstoqueZerado());
    }

    // 🔹 Deletar
    @DeleteMapping("/{idEstoque}")
    public ResponseEntity<Void> deletar(@PathVariable Long idEstoque) {
        estoqueService.deletar(idEstoque);
        return ResponseEntity.noContent().build();
    }
}
