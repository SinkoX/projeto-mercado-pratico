package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.TelefoneUsuario;

@Repository
public interface TelefoneUsuarioRepository extends JpaRepository<TelefoneUsuario, Long> {

    // Buscar todos os telefones de um usuário pelo id do usuário
    List<TelefoneUsuario> findByUsuario_IdUsuario(Long id);

    // Buscar por número exato
    List<TelefoneUsuario> findByNumeroTelefoneUsuario(String numeroTelefoneUsuario);

    // Buscar por número ignorando case
    List<TelefoneUsuario> findByNumeroTelefoneUsuarioIgnoreCase(String numeroTelefoneUsuario);

    // Verificar existência pelo número
    boolean existsByNumeroTelefoneUsuario(String numeroTelefoneUsuario);
}
