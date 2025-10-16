package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senaidev.prjMercadoPratico.dto.CarrinhoDTO;
import com.senaidev.prjMercadoPratico.entities.Carrinho;
import com.senaidev.prjMercadoPratico.services.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CarrinhoDTO> buscarCarrinho(@PathVariable Long idUsuario) {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorUsuario(idUsuario);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }

    @PostMapping("/{idUsuario}/adicionar/{idProduto}")
    public ResponseEntity<CarrinhoDTO> adicionarItem(@PathVariable Long idUsuario,
                                                     @PathVariable Long idProduto,
                                                     @RequestParam Integer quantidade) {
        Carrinho carrinho = carrinhoService.adicionarItem(idUsuario, idProduto, quantidade);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }

    @DeleteMapping("/{idUsuario}/remover/{idProduto}")
    public ResponseEntity<CarrinhoDTO> removerItem(@PathVariable Long idUsuario,
                                                   @PathVariable Long idProduto) {
        Carrinho carrinho = carrinhoService.removerItem(idUsuario, idProduto);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }

    @DeleteMapping("/{idUsuario}/limpar")
    public ResponseEntity<CarrinhoDTO> limparCarrinho(@PathVariable Long idUsuario) {
        Carrinho carrinho = carrinhoService.limparCarrinho(idUsuario);
        return ResponseEntity.ok(new CarrinhoDTO(carrinho));
    }
}
