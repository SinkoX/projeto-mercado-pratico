package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.TelefoneUsuario;
import com.senaidev.prjMercadoPratico.repositories.TelefoneUsuarioRepository;

@Service
public class TelefoneUsuarioService {

    @Autowired
    private TelefoneUsuarioRepository telefoneUsuarioRepository;

    // Buscar todos os telefones
    public List<TelefoneUsuario> findAll() {
        return telefoneUsuarioRepository.findAll();
    }

    // Buscar telefone por ID
    public TelefoneUsuario findById(Long id) {
        Optional<TelefoneUsuario> telefone = telefoneUsuarioRepository.findById(id);
        return telefone.orElseThrow(() -> new RuntimeException("Telefone do cliente não encontrado com ID: " + id));
    }

    // Inserir novo telefone
    public TelefoneUsuario insert(TelefoneUsuario telefone) {
        return telefoneUsuarioRepository.save(telefone);
    }

    // Atualizar telefone existente
    public TelefoneUsuario update(Long id, TelefoneUsuario novoTelefone) {
        TelefoneUsuario telefone = findById(id);
        telefone.setNumeroTelefoneUsuario(novoTelefone.getNumeroTelefoneUsuario());
        telefone.setUsuario(novoTelefone.getUsuario());
        return telefoneUsuarioRepository.save(telefone);
    }

    // Deletar telefone por ID
    public void delete(Long id) {
        telefoneUsuarioRepository.deleteById(id);
    }

    // Buscar todos os telefones de um cliente (usuário)
    public List<TelefoneUsuario> findByUsuario(Long idUsuario) {
        return telefoneUsuarioRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Buscar telefones por número exato
    public List<TelefoneUsuario> findByNumeroTelefone(String numero) {
        return telefoneUsuarioRepository.findByNumeroTelefone(numero);
    }

    // Buscar telefones por número (ignorando maiúsculas/minúsculas)
    public List<TelefoneUsuario> findByNumeroTelefoneIgnoreCase(String numero) {
        return telefoneUsuarioRepository.findByNumeroTelefoneIgnoreCase(numero);
    }

    // Verificar se um número já está cadastrado
    public boolean existsByNumeroTelefone(String numero) {
        return telefoneUsuarioRepository.existsByNumeroTelefone(numero);
    }
}
