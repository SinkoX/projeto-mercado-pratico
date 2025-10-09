package com.senaidev.prjMercadoPratico.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    // Buscar por ID
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    // Buscar por nome (contém, ignore case)
    public List<Produto> findByNome(String nome) {
        return produtoRepository.findByNomeProdutoContainingIgnoreCase(nome);
    }

    // 🔹 Buscar por subcategoria
    public List<Produto> findBySubcategoria(Subcategoria subcategoria) {
        return produtoRepository.findBySubcategoria(subcategoria);
    }

    // 🔹 Buscar por categoria (agora via subcategoria)
    public List<Produto> findByCategoria(Categoria categoria) {
        return produtoRepository.findBySubcategoria_Categoria(categoria);
    }

    // Buscar produtos vencidos (data de validade antes de hoje)
    public List<Produto> findProdutosVencidos() {
        return produtoRepository.findByDataValidadeBefore(LocalDate.now());
    }

    // Inserir produto
    public Produto insert(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    public Produto update(Long id, Produto novoProduto) {
        Produto produto = findById(id); // lança exceção se não encontrar
        produto.setNomeProduto(novoProduto.getNomeProduto());
        produto.setPrecoProduto(novoProduto.getPrecoProduto());
        produto.setQuantidade(novoProduto.getQuantidade());
        produto.setDataValidade(novoProduto.getDataValidade());
        produto.setSubcategoria(novoProduto.getSubcategoria()); // ✅ atualizar subcategoria também
        return produtoRepository.save(produto);
    }

    // Remover produto
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    // Salvar imagem do produto
    public void salvarImagem(Long idProduto, MultipartFile imagem) throws IOException {
        Produto produto = findById(idProduto);
        produto.setImagemProduto(imagem.getBytes());
        produtoRepository.save(produto);
    }
}
