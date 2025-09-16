package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senaidev.prjMercadoPratico.entities.TelefoneFuncionario;

public interface TelefoneFuncionarioRepository extends JpaRepository<TelefoneFuncionario, Long> {

    // Buscar telefones pelo id do funcionário
    List<TelefoneFuncionario> findByFuncionarioIdFuncionario(Long idFuncionario);

    // Buscar telefone pelo número (exato)
    List<TelefoneFuncionario> findByNumeroTelefone(String numeroTelefone);

    // Buscar telefone pelo número ignorando maiúsculas/minúsculas
    List<TelefoneFuncionario> findByNumeroTelefoneIgnoreCase(String numeroTelefone);
}
