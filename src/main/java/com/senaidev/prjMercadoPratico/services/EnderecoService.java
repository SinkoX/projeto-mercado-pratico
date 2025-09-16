package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.Endereco;
import com.senaidev.prjMercadoPratico.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // 🔍 Buscar todos os endereços
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    // Buscar por ID
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));
    }

    // Buscar por ID do usuário (cliente)
    public List<Endereco> findByUsuarioId(Long idUsuario) {
        return enderecoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Criar endereço
    public Endereco insert(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Atualizar
    public Endereco update(Long id, Endereco novoEndereco) {
        Endereco endereco = findById(id);
        endereco.setRua(novoEndereco.getRua());
        endereco.setNumero(novoEndereco.getNumero());
        endereco.setBairro(novoEndereco.getBairro());
        endereco.setCidade(novoEndereco.getCidade());
        endereco.setEstado(novoEndereco.getEstado());
        endereco.setCep(novoEndereco.getCep());
        endereco.setComplemento(novoEndereco.getComplemento());
        return enderecoRepository.save(endereco);
    }

    // Deletar
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }
}
