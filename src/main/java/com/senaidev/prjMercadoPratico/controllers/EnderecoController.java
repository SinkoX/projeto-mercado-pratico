package com.senaidev.prjMercadoPratico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senaidev.prjMercadoPratico.entities.Endereco;
import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.EnderecoRepository;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	 @Autowired
	    private EnderecoRepository enderecoRepository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    // 🔍 Buscar todos os endereços
	    @GetMapping
	    public List<Endereco> listarTodos() {
	        return enderecoRepository.findAll();
	    }

	    // 🔍 Buscar endereço por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
	        Optional<Endereco> endereco = enderecoRepository.findById(id);
	        return endereco.map(ResponseEntity::ok)
	                       .orElse(ResponseEntity.notFound().build());
	    }

	    // ➕ Criar novo endereço (com ID do usuário relacionado)
	    @PostMapping("/usuario/{usuarioId}")
	    public ResponseEntity<Endereco> criar(@PathVariable Long usuarioId, @RequestBody Endereco endereco) {
	        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
	        if (usuarioOptional.isPresent()) {
	            endereco.setUsuario(usuarioOptional.get());
	            Endereco salvo = enderecoRepository.save(endereco);
	            return ResponseEntity.ok(salvo);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // ✏️ Atualizar endereço
	    @PutMapping("/{id}")
	    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
	        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
	        if (enderecoOptional.isPresent()) {
	            Endereco existente = enderecoOptional.get();
	            existente.setRua(enderecoAtualizado.getRua());
	            existente.setNumero(enderecoAtualizado.getNumero());
	            existente.setBairro(enderecoAtualizado.getBairro());
	            existente.setCidade(enderecoAtualizado.getCidade());
	            existente.setEstado(enderecoAtualizado.getEstado());
	            existente.setCep(enderecoAtualizado.getCep());
	            existente.setComplemento(enderecoAtualizado.getComplemento());

	            // opcional: mudar o usuário associado
	            if (enderecoAtualizado.getUsuario() != null) {
	                existente.setUsuario(enderecoAtualizado.getUsuario());
	            }

	            enderecoRepository.save(existente);
	            return ResponseEntity.ok(existente);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    //Deletar endereço
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletar(@PathVariable Long id) {
	        if (enderecoRepository.existsById(id)) {
	            enderecoRepository.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Buscar endereços por ID de usuário
	    @GetMapping("/usuario/{usuarioId}")
	    public ResponseEntity<List<Endereco>> listarPorUsuario(@PathVariable Long usuarioId) {
	        if (!usuarioRepository.existsById(usuarioId)) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Endereco> enderecos = enderecoRepository.findByUsuarioIdUsuario(usuarioId);
	        return ResponseEntity.ok(enderecos);
	    }

}
