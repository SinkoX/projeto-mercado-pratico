package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senaidev.prjMercadoPratico.entities.TelefoneUsuario;

public interface TelefoneUsuarioRepository extends JpaRepository<TelefoneUsuario, Long> {

    // Buscar todos os telefones de um cliente (usuário)
    List<TelefoneUsuario> findByUsuarioIdUsuario(Long idUsuario);

    // Buscar telefone por número (exato)
    List<TelefoneUsuario> findByNumeroTelefoneUsuario(String numeroTelefoneUsuario);

    // Buscar telefone por número ignorando maiúsculas/minúsculas
    List<TelefoneUsuario> findByNumeroTelefoneUsuarioIgnoreCase(String numeroTelefoneUsuario);

    // Verificar se um número de telefone já está cadastrado
    boolean existsByNumeroTelefoneUsuario(String numeroTelefoneUsuario);
}
