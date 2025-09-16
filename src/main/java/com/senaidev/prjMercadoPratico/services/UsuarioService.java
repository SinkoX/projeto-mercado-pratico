package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Buscar todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // Inserir novo usuário
    public Usuario insert(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Atualizar usuário existente
    public Usuario update(Long id, Usuario novoUsuario) {
        Usuario usuarioExistente = findById(id);

        usuarioExistente.setEmailUsuario(novoUsuario.getEmailUsuario());
        usuarioExistente.setSenhaUsuario(novoUsuario.getSenhaUsuario());
        usuarioExistente.setTipoUsuario(novoUsuario.getTipoUsuario());
        // Adicione mais campos conforme necessário

        return usuarioRepository.save(usuarioExistente);
    }

    // Deletar usuário por ID
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Buscar por e-mail
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Buscar por CPF
    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}
