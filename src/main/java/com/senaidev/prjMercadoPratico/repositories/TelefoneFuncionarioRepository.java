package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senaidev.prjMercadoPratico.entities.TelefoneFuncionario;

public interface TelefoneFuncionarioRepository extends JpaRepository<TelefoneFuncionario, Long> {

    // CORRETO
    List<TelefoneFuncionario> findByFuncionario_Id(Long idFuncionario);

    List<TelefoneFuncionario> findByNumeroTelefoneFuncionario(String numeroTelefoneFuncionario);

    List<TelefoneFuncionario> findByNumeroTelefoneFuncionarioIgnoreCase(String numeroTelefoneFuncionario);
}
