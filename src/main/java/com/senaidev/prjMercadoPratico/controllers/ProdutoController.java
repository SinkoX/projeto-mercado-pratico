package com.senaidev.prjMercadoPratico.controllers;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import com.senaidev.prjMercadoPratico.repositories.SubcategoriaRepository;
import com.senaidev.prjMercadoPratico.services.ProdutoService;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        return ResponseEntity.ok(produtoService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findByIdDTO(id));
    }

    // Cadastro simples com rota fixa
    @PostMapping("/cadastro")
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto) {
        ProdutoDTO novoProduto = produtoService.insertDTO(dto);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.updateDTO(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/com-imagem")
    public ResponseEntity<String> cadastrarComImagem(
            @RequestParam("nomeProduto") String nomeProduto,
            @RequestParam("precoProduto") BigDecimal precoProduto,
            @RequestParam("quantidade") Integer quantidade,
            @RequestParam("dataValidade") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataValidade,
            @RequestParam("idSubcategoria") Long idSubcategoria,
            @RequestParam(value = "imagemProduto", required = false) MultipartFile imagemProduto
    ) {
        try {
            Subcategoria subcategoria = subcategoriaRepository.findById(idSubcategoria)
                    .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada"));

            Produto produto = new Produto();
            produto.setNomeProduto(nomeProduto);
            produto.setPrecoProduto(precoProduto);
            produto.setQuantidade(quantidade);
            produto.setDataValidade(dataValidade);
            produto.setSubcategoria(subcategoria);

            if (imagemProduto != null && !imagemProduto.isEmpty()) {
                produto.setImagemProduto(imagemProduto.getBytes());
            }

            produtoRepository.save(produto);
            return ResponseEntity.ok("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao cadastrar produto");
        }
    }
}
