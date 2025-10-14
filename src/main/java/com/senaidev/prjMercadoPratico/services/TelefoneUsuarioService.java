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

    public List<TelefoneUsuario> findAll() {
        return telefoneUsuarioRepository.findAll();
    }

    public TelefoneUsuario findById(Long id) {
        Optional<TelefoneUsuario> telefone = telefoneUsuarioRepository.findById(id);
        return telefone.orElseThrow(() -> new RuntimeException("Telefone do cliente não encontrado com ID: " + id));
    }

    public TelefoneUsuario insert(TelefoneUsuario telefone) {
        return telefoneUsuarioRepository.save(telefone);
    }

    public TelefoneUsuario update(Long id, TelefoneUsuario novoTelefone) {
        TelefoneUsuario telefone = findById(id);
        telefone.setNumeroTelefoneUsuario(novoTelefone.getNumeroTelefoneUsuario());
        telefone.setUsuario(novoTelefone.getUsuario());
        return telefoneUsuarioRepository.save(telefone);
    }

    public void delete(Long id) {
        telefoneUsuarioRepository.deleteById(id);
    }

    // Corrigido: Retorna lista de telefones do usuário pelo id do usuário
    public List<TelefoneUsuario> findByUsuario(Long id) {
        return telefoneUsuarioRepository.findByUsuario_IdUsuario(id);
    }

    public List<TelefoneUsuario> findByNumeroTelefoneUsuario(String numeroTelefoneUsuario) {
        return telefoneUsuarioRepository.findByNumeroTelefoneUsuario(numeroTelefoneUsuario);
    }

    public List<TelefoneUsuario> findByNumeroTelefoneUsuarioIgnoreCase(String numeroTelefoneUsuario) {
        return telefoneUsuarioRepository.findByNumeroTelefoneUsuarioIgnoreCase(numeroTelefoneUsuario);
    }

    public boolean existsByNumeroTelefoneUsuario(String numeroTelefoneUsuario) {
        return telefoneUsuarioRepository.existsByNumeroTelefoneUsuario(numeroTelefoneUsuario);
    }
}
