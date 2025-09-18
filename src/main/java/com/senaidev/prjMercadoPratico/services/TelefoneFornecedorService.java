package com.senaidev.prjMercadoPratico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.TelefoneFornecedor;
import com.senaidev.prjMercadoPratico.repositories.TelefoneFornecedorRepository;

@Service
public class TelefoneFornecedorService {

    @Autowired
    private TelefoneFornecedorRepository telefoneFornecedorRepository;

    // Buscar todos os telefones
    public List<TelefoneFornecedor> findAll() {
        return telefoneFornecedorRepository.findAll();
    }

    // Buscar telefone por ID
    public TelefoneFornecedor findById(Long id) {
        Optional<TelefoneFornecedor> telefone = telefoneFornecedorRepository.findById(id);
        return telefone.orElseThrow(() -> new RuntimeException("Telefone do fornecedor não encontrado com ID: " + id));
    }

    // Inserir novo telefone
    public TelefoneFornecedor insert(TelefoneFornecedor telefone) {
        return telefoneFornecedorRepository.save(telefone);
    }

    // Atualizar telefone existente
    public TelefoneFornecedor update(Long id, TelefoneFornecedor novoTelefone) {
        TelefoneFornecedor telefone = findById(id);
        telefone.setNumeroTelefoneFornecedor(novoTelefone.getNumeroTelefoneFornecedor());
        telefone.setFornecedor(novoTelefone.getFornecedor());
        return telefoneFornecedorRepository.save(telefone);
    }

    // Deletar telefone por ID
    public void delete(Long id) {
        telefoneFornecedorRepository.deleteById(id);
    }

    // Buscar telefones por ID do fornecedor
    public List<TelefoneFornecedor> findByFornecedorId(Long idFornecedor) {
        return telefoneFornecedorRepository.findByFornecedorIdFornecedor(idFornecedor);
    }

    // Buscar telefones por número exato
    public List<TelefoneFornecedor> findByNumeroTelefone(String numero) {
        return telefoneFornecedorRepository.findByNumeroTelefone(numero);
    }

    // Buscar telefones por número ignorando maiúsculas/minúsculas
    public List<TelefoneFornecedor> findByNumeroTelefoneIgnoreCase(String numero) {
        return telefoneFornecedorRepository.findByNumeroTelefoneIgnoreCase(numero);
    }
}
