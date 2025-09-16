package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    List<Endereco> findByUsuarioIdUsuario(Long idUsuario);

    List<Endereco> findByCep(String cep);

    List<Endereco> findByCidade(String cidade);

    List<Endereco> findByEstado(String estado);

    List<Endereco> findByCidadeAndEstado(String cidade, String estado);

    List<Endereco> findByRuaContainingIgnoreCase(String rua);

    boolean existsByUsuarioIdUsuarioAndCep(Long idUsuario, String cep);

    List<Endereco> findAllByOrderByCidadeAsc();
}
