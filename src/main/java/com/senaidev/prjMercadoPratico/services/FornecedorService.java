package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.Fornecedor;
import com.senaidev.prjMercadoPratico.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public List<Fornecedor> findByNomeExato(String nome) {
        return fornecedorRepository.findByNomeFornecedor(nome);
    }

    public List<Fornecedor> findByNomeContendo(String nome) {
        return fornecedorRepository.findByNomeFornecedorContainingIgnoreCase(nome);
    }

    public Optional<Fornecedor> findByEmail(String email) {
        return fornecedorRepository.findByEmailFornecedor(email);
    }

    public boolean existsByCpf(String cpf) {
        return fornecedorRepository.existsByCpfFornecedor(cpf);
    }

    public boolean existsByCnpj(String cnpj) {
        return fornecedorRepository.existsByCnpj(cnpj);
    }

    public Optional<Fornecedor> findByCpfOrCnpj(String cpf, String cnpj) {
        return fornecedorRepository.findByCpfFornecedorOrCnpj(cpf, cnpj);
    }

    public Fornecedor insert(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor update(Long id, Fornecedor novoFornecedor) {
        Fornecedor fornecedor = findById(id);
        fornecedor.setNomeFornecedor(novoFornecedor.getNomeFornecedor());
        fornecedor.setEmailFornecedor(novoFornecedor.getEmailFornecedor());
        fornecedor.setCpfFornecedor(novoFornecedor.getCpfFornecedor());
        fornecedor.setCnpj(novoFornecedor.getCnpj());
        // Atualize outros campos conforme necessário
        return fornecedorRepository.save(fornecedor);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
