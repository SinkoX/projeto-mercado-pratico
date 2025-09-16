package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.TipoUsuario;
import com.senaidev.prjMercadoPratico.repositories.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    // Buscar todos os tipos de usuários
    public List<TipoUsuario> findAll() {
        return tipoUsuarioRepository.findAll();
    }

    // Buscar tipo de usuário por ID
    public TipoUsuario findById(Long id) {
        return tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado com ID: " + id));
    }

    // Inserir novo tipo de usuário
    public TipoUsuario insert(TipoUsuario tipoUsuario) {
        if (tipoUsuarioRepository.existsByNomeTipoUsuario(tipoUsuario.getNomeTipoUsuario())) {
            throw new RuntimeException("Já existe um tipo de usuário com o nome: " + tipoUsuario.getNomeTipoUsuario());
        }
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    // Atualizar tipo de usuário existente
    public TipoUsuario update(Long id, TipoUsuario novoTipo) {
        TipoUsuario tipo = findById(id);
        tipo.setNomeTipoUsuario(novoTipo.getNomeTipoUsuario());
        tipo.setDescricao(novoTipo.getDescricao());
        return tipoUsuarioRepository.save(tipo);
    }

    // Deletar tipo de usuário por ID
    public void delete(Long id) {
        if (!tipoUsuarioRepository.existsById(id)) {
            throw new RuntimeException("Tipo de usuário não encontrado com ID: " + id);
        }
        tipoUsuarioRepository.deleteById(id);
    }

    // Buscar tipo de usuário por nome (exato)
    public Optional<TipoUsuario> findByNome(String nome) {
        return tipoUsuarioRepository.findByNomeTipoUsuario(nome);
    }

    // Verificar se já existe tipo de usuário com o nome
    public boolean existsByNome(String nome) {
        return tipoUsuarioRepository.existsByNomeTipoUsuario(nome);
    }
}
