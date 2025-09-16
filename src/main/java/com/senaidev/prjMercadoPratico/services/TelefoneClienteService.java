package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.TelefoneCliente;
import com.senaidev.prjMercadoPratico.repositories.TelefoneClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneClienteService {

    @Autowired
    private TelefoneClienteRepository telefoneClienteRepository;

    // Buscar todos os telefones
    public List<TelefoneCliente> findAll() {
        return telefoneClienteRepository.findAll();
    }

    // Buscar telefone por ID
    public TelefoneCliente findById(Long id) {
        Optional<TelefoneCliente> telefone = telefoneClienteRepository.findById(id);
        return telefone.orElseThrow(() -> new RuntimeException("Telefone do cliente não encontrado com ID: " + id));
    }

    // Inserir novo telefone
    public TelefoneCliente insert(TelefoneCliente telefone) {
        return telefoneClienteRepository.save(telefone);
    }

    // Atualizar telefone existente
    public TelefoneCliente update(Long id, TelefoneCliente novoTelefone) {
        TelefoneCliente telefone = findById(id);
        telefone.setNumeroTelefone(novoTelefone.getNumeroTelefone());
        telefone.setUsuario(novoTelefone.getUsuario());
        return telefoneClienteRepository.save(telefone);
    }

    // Deletar telefone por ID
    public void delete(Long id) {
        telefoneClienteRepository.deleteById(id);
    }

    // Buscar todos os telefones de um cliente (usuário)
    public List<TelefoneCliente> findByUsuario(Long idUsuario) {
        return telefoneClienteRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Buscar telefones por número exato
    public List<TelefoneCliente> findByNumeroTelefone(String numero) {
        return telefoneClienteRepository.findByNumeroTelefone(numero);
    }

    // Buscar telefones por número (ignorando maiúsculas/minúsculas)
    public List<TelefoneCliente> findByNumeroTelefoneIgnoreCase(String numero) {
        return telefoneClienteRepository.findByNumeroTelefoneIgnoreCase(numero);
    }

    // Verificar se um número já está cadastrado
    public boolean existsByNumeroTelefone(String numero) {
        return telefoneClienteRepository.existsByNumeroTelefone(numero);
    }
}
