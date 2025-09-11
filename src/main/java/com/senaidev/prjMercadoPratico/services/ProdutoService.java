package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto insert(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto novoProduto) {
        Produto produto = findById(id);
        produto.setNomeProduto(novoProduto.getNomeProduto());
        produto.setPrecoProduto(novoProduto.getPrecoProduto());
        // outros setters...
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
