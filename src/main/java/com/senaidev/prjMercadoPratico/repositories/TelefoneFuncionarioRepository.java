package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.TelefoneFuncionario;

@Repository
public interface TelefoneFuncionarioRepository extends JpaRepository<TelefoneFuncionario, Long> {

    // CORRETO
    List<TelefoneFuncionario> findByFuncionario_Id(Long idFuncionario);

    List<TelefoneFuncionario> findByNumeroTelefoneFuncionario(String numeroTelefoneFuncionario);

    List<TelefoneFuncionario> findByNumeroTelefoneFuncionarioIgnoreCase(String numeroTelefoneFuncionario);
}
