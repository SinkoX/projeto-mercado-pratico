package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senaidev.prjMercadoPratico.entities.TelefoneFuncionario;

public interface TelefoneFuncionarioRepository extends JpaRepository<TelefoneFuncionario, Long> {
	List<TelefoneFuncionario> findByFuncionarioIdUsuario(Long idUsuario);

    // Buscar telefone pelo número (exato)
    List<TelefoneFuncionario> findByNumeroTelefoneFuncionario(String numeroTelefoneFuncionario);

    // Buscar telefone pelo número ignorando maiúsculas/minúsculas
    List<TelefoneFuncionario> findByNumeroTelefoneFuncionarioIgnoreCase(String numeroTelefoneFuncionario);
}
