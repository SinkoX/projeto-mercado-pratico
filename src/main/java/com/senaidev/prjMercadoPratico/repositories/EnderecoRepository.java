package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    List<Endereco> findByUsuarioIdUsuario(Long idUsuario);

    List<Endereco> findByCep(String cep);

    boolean existsByUsuarioIdUsuarioAndCep(Long idUsuario, String cep);
}
