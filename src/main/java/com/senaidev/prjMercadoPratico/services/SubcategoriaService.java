package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.Subcategoria;
import com.senaidev.prjMercadoPratico.repositories.SubcategoriaRepository;

@Service
public class SubcategoriaService {

	@Autowired
    private SubcategoriaRepository subcategoriaRepository;

    //Listar todos os subcategorias
    public List<Subcategoria> findAll() {
        return subcategoriaRepository.findAll();
    }

    // Buscar subcategoria por ID
    public Optional<Subcategoria> findById(Long id) {
        return subcategoriaRepository.findById(id);
    }

    // Buscar por nome da subcategoria
    public List<Subcategoria> findByNomeSubcategoria(String subcategoria) {
        return subcategoriaRepository.findByNomeSubcategoria(subcategoria);
    }

    // Buscar por nome da subcategoria
    public List<Subcategoria> findByNomeSubcategoriaIgnoreCase(String subcategoria) {
        return subcategoriaRepository.findByNomeSubcategoriaIgnoreCase(subcategoria);
    }
    
    // Cadastrar nova subcategoria
    public Subcategoria save(Subcategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }
    
    // Atualizar subcategoria existente
    public Subcategoria update(Long id, Subcategoria novaSubcategoria) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id).orElse(null);
        if (subcategoria != null) {
            subcategoria.setNomeSubcategoria(novaSubcategoria.getNomeSubcategoria());
            return subcategoriaRepository.save(subcategoria);
        } else {
            // Caso a subcategoria não seja encontrada, pode lançar exceção ou retornar uma resposta específica
            throw new RuntimeException("Subcategoria não encontrada para o ID: " + id);
        }
    }
    
    // Deletar subcategoria por ID
    public void delete(Long id) {
        subcategoriaRepository.deleteById(id);
    }
}

