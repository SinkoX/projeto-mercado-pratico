package com.senaidev.prjMercadoPratico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.TipoUsuario;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    //  Buscar por nome do tipo de usuário (exato)
    Optional<TipoUsuario> findByNomeTipoUsuario(String nomeTipoUsuario);

    //  Verificar se já existe um tipo de usuário com determinado nome
    boolean existsByNomeTipoUsuario(String nomeTipoUsuario);
}
