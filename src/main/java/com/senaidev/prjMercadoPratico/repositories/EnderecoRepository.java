package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    List<Endereco> findByUsuarioId(Long id);

    List<Endereco> findByCep(String cep);

    boolean existsByUsuario_IdAndCep(Long idUsuario, String cep);

}
