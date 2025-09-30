package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    List<Endereco> findByUsuarioId(Long id);

    List<Endereco> findByCep(String cep);

    boolean existsByUsuario_IdAndCep(Long idUsuario, String cep);

}
