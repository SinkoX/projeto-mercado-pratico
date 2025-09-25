package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.TelefoneFuncionario;
import com.senaidev.prjMercadoPratico.repositories.TelefoneFuncionarioRepository;

@Service
public class TelefoneFuncionarioService {

    @Autowired
    private TelefoneFuncionarioRepository telefoneFuncionarioRepository;

    // Buscar todos os telefones
    public List<TelefoneFuncionario> findAll() {
        return telefoneFuncionarioRepository.findAll();
    }

    // Buscar telefone por ID
    public TelefoneFuncionario findById(Long id) {
        Optional<TelefoneFuncionario> telefone = telefoneFuncionarioRepository.findById(id);
        return telefone.orElseThrow(() -> new RuntimeException("Telefone do funcionário não encontrado com ID: " + id));
    }

    // Inserir novo telefone
    public TelefoneFuncionario insert(TelefoneFuncionario telefone) {
        return telefoneFuncionarioRepository.save(telefone);
    }

    // Atualizar telefone existente
    public TelefoneFuncionario update(Long id, TelefoneFuncionario novoTelefone) {
        TelefoneFuncionario telefone = findById(id);
        telefone.setNumeroTelefoneFuncionario(novoTelefone.getNumeroTelefoneFuncionario());
        telefone.setFuncionario(novoTelefone.getFuncionario());
        return telefoneFuncionarioRepository.save(telefone);
    }

    // Deletar telefone por ID
    public void delete(Long id) {
        telefoneFuncionarioRepository.deleteById(id);
    }

    // Buscar telefones por ID do funcionário
    public List<TelefoneFuncionario> findByFuncionarioIdUsuario(Long idFuncionario) {
        return telefoneFuncionarioRepository.findByFuncionarioIdUsuario(idFuncionario);
    }

    // Buscar telefones por número exato
    public List<TelefoneFuncionario> findByNumeroTelefoneFuncionario(String numeroTelefoneFuncionario) {
        return telefoneFuncionarioRepository.findByNumeroTelefoneFuncionario(numeroTelefoneFuncionario);
    }

    // Buscar telefones por número ignorando maiúsculas/minúsculas
    public List<TelefoneFuncionario> findByNumeroTelefoneFuncionarioIgnoreCase(String numeroTelefoneFuncionario) {
        return telefoneFuncionarioRepository.findByNumeroTelefoneFuncionarioIgnoreCase(numeroTelefoneFuncionario);
    }
}
