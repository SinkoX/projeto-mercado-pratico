package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Buscar funcionário por nome (exato)
    List<Funcionario> findByNomeFuncionario(String nomeFuncionario);

    // Buscar por nome ignorando maiúsculas/minúsculas
    List<Funcionario> findByNomeFuncionarioIgnoreCase(String nomeFuncionario);

    // Buscar por cargo
    List<Funcionario> findByCargoIgnoreCase(String cargo);

    //Buscar por nome contendo (like %nome%)
    List<Funcionario> findByNomeFuncionarioContainingIgnoreCase(String nomeFragmento);

    //Verificar se existe funcionário com determinado e-mail
    boolean existsByEmailUsuario(String emailUsuario);
}
