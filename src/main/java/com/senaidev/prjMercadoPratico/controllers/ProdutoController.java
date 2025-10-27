package com.senaidev.prjMercadoPratico.controllers;

import com.senaidev.prjMercadoPratico.dto.ProdutoDTO;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.services.ProdutoService;
import com.senaidev.prjMercadoPratico.repositories.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    // Método para cadastrar produto
    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoDTO dto) {
        try {
            // Buscar a subcategoria com base no ID
            Subcategoria subcategoria = subcategoriaRepository.findById(dto.getIdSubcategoria())
                    .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada"));

            // Criar e preencher o produto
            Produto produto = new Produto();
            produto.setNomeProduto(dto.getNomeProduto());
            produto.setQuantidade(dto.getQuantidade());
            produto.setPrecoProduto(dto.getPrecoProduto());
            produto.setDataValidade(dto.getDataValidade());
            produto.setSubcategoria(subcategoria);
            produto.setImgUrl(dto.getImgUrl());

            // Verificar se a descrição foi fornecida e atribuir
            if (dto.getDescricaoProduto() != null && !dto.getDescricaoProduto().isEmpty()) {
                produto.setDescricao(dto.getDescricaoProduto());
            }

            // Verificar se a imagem foi passada em base64 e decodificar
            if (dto.getImagemProdutoBase64() != null && !dto.getImagemProdutoBase64().isEmpty()) {
                String base64Image = dto.getImagemProdutoBase64().split(",")[1]; // Remove o prefixo "data:image/png;base64,"
                byte[] imagemBytes = Base64.getDecoder().decode(base64Image);
                produto.setImagemProduto(imagemBytes);
            }

            // Salvar o produto no banco de dados
            Produto novoProduto = produtoService.salvar(produto);

            // Retornar o produto salvo como DTO
            return ResponseEntity.ok(new ProdutoDTO(novoProduto));

        } catch (Exception e) {
            // Retornar mensagem de erro
            return ResponseEntity.badRequest().body("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    // Método para buscar todos os produtos
    @GetMapping
    public ResponseEntity<?> listarProdutos() {
        try {
            return ResponseEntity.ok(produtoService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar produtos: " + e.getMessage());
        }
    }

    // Método para buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id) {
        try {
            ProdutoDTO produtoDTO = produtoService.findByIdDTO(id);
            return ResponseEntity.ok(produtoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Produto não encontrado: " + e.getMessage());
        }
    }

    // Método para atualizar um produto
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        try {
            ProdutoDTO produtoAtualizado = produtoService.updateDTO(id, dto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // Método para deletar um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
