package com.senaidev.prjMercadoPratico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public Usuario insert(Usuario usuario) {
        // 🔥 CRIPTOGRAFA A SENHA ANTES DE SALVAR
        usuario.setSenhaUsuario(encoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario novoUsuario) {
        Usuario usuarioExistente = findById(id);

        usuarioExistente.setEmailUsuario(novoUsuario.getEmailUsuario());

        // Se alterar senha, criptografa novamente
        usuarioExistente.setSenhaUsuario(encoder.encode(novoUsuario.getSenhaUsuario()));

        usuarioExistente.setCpfUsuario(novoUsuario.getCpfUsuario());
        usuarioExistente.setTipoUsuario(novoUsuario.getTipoUsuario());
        usuarioExistente.setTelefoneUsuario(novoUsuario.getTelefoneUsuario());

        return usuarioRepository.save(usuarioExistente);
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }

    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpfUsuario(cpf);
    }

  
}
