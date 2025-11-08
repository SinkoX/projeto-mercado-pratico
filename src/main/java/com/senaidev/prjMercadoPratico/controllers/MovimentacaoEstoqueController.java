package com.senaidev.prjMercadoPratico.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.MovimentacaoEstoqueDTO;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;
import com.senaidev.prjMercadoPratico.services.MovimentacaoEstoqueService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    // 🔹 Listar todas
    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listarTodas() {
        return ResponseEntity.ok(movimentacaoService.listarTodas());
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.buscarPorId(id));
    }

    // 🔹 Buscar por produto
    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> buscarPorProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(movimentacaoService.buscarPorProduto(idProduto));
    }

    // 🔹 Buscar por tipo
    @GetMapping("/tipo")
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> buscarPorTipo(@RequestParam TipoMovimentacao tipo) {
        return ResponseEntity.ok(movimentacaoService.buscarPorTipo(tipo));
    }
    
    // 🔹 Buscar por entradas de produtos
    @GetMapping("/entradas")
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listarEntradas() {
        return ResponseEntity.ok(movimentacaoService.buscarPorTipo(TipoMovimentacao.ENTRADA));
    }

    // 🔹 Buscar por saídas de produtos
    @GetMapping("/saidas")
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listarSaidas() {
        return ResponseEntity.ok(movimentacaoService.buscarPorTipo(TipoMovimentacao.SAIDA));
    }


    // 🔹 Buscar por período
    @GetMapping("/periodo")
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

        return ResponseEntity.ok(movimentacaoService.buscarPorPeriodo(inicio, fim));
    }

    // 🔹 Registrar movimentação manual
    @PostMapping("/manual")
    public ResponseEntity<MovimentacaoEstoqueDTO> registrarMovimentacaoManual(
            @RequestParam Long idProduto,
            @RequestParam Integer quantidade,
            @RequestParam TipoMovimentacao tipo,
            @RequestParam(required = false) String observacao) {

        return ResponseEntity.ok(
                movimentacaoService.registrarMovimentacaoManual(idProduto, quantidade, tipo, observacao)
        );
    }

    // 🔹 Últimas movimentações
    @GetMapping("/ultimas")
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> buscarUltimasMovimentacoes() {
        return ResponseEntity.ok(movimentacaoService.buscarUltimasMovimentacoes());
    }
}
