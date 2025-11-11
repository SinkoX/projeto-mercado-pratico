package com.senaidev.prjMercadoPratico.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.MovimentacaoEstoqueDTO;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;
import com.senaidev.prjMercadoPratico.services.MovimentacaoEstoqueService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService service;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimentacaoEstoqueDTO> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public MovimentacaoEstoqueDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/produto/{idProduto}")
    public List<MovimentacaoEstoqueDTO> buscarPorProduto(@PathVariable Long idProduto) {
        return service.buscarPorProduto(idProduto);
    }

    @GetMapping("/tipo/{tipo}")
    public List<MovimentacaoEstoqueDTO> buscarPorTipo(@PathVariable TipoMovimentacao tipo) {
        return service.buscarPorTipo(tipo);
    }

    @GetMapping("/periodo")
    public List<MovimentacaoEstoqueDTO> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return service.buscarPorPeriodo(inicio, fim);
    }

    // 🔹 Registrar movimentação manual via JSON
    @PostMapping("/manual")
    public MovimentacaoEstoqueDTO registrarManual(@RequestBody MovimentacaoEstoqueDTO dto) {
        return service.registrarMovimentacaoManual(dto);
    }

    @GetMapping("/ultimas")
    public List<MovimentacaoEstoqueDTO> ultimasMovimentacoes() {
        return service.buscarUltimasMovimentacoes();
    }
}
