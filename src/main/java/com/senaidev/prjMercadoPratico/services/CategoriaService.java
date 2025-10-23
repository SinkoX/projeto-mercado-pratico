package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.Categoria;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.repositories.CategoriaRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository; // ✅ injetado

    //Listar todos os subcategorias
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    // Buscar subcategoria por ID
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    // Buscar por nome da subcategoria
    public List<Categoria> findByNomeCategoria(String categoria) {
        return categoriaRepository.findByNomeCategoria(categoria);
    }

    // Buscar por nome da subcategoria
    public List<Categoria> findByNomeCategoriaIgnoreCase(String categoria) {
        return categoriaRepository.findByNomeCategoriaIgnoreCase(categoria);
    }
    
    // Cadastrar nova subcategoria
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    // Atualizar subcategoria existente
    public Categoria update(Long id, Categoria novaCategoria) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null); 
        if (categoria != null) {
            categoria.setNomeCategoria(novaCategoria.getNomeCategoria());
            return categoriaRepository.save(categoria);
        } else {
            throw new RuntimeException("Categoria não encontrada para o ID: " + id);
        }
    }
    
    // Deletar categoria por ID
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    // ✅ Método para buscar produtos pelo nome da categoria
    public List<Produto> getProdutosPorNomeCategoria(String nomeCategoria) {
        Categoria categoria = categoriaRepository.findByNomeCategoriaIgnoreCase(nomeCategoria)
                                .stream()
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return produtoRepository.findBySubcategoria_Categoria(categoria); // usar o método correto do repo
    }
}
