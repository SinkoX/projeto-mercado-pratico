package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.TelefoneCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneClienteRepository extends JpaRepository<TelefoneCliente, Long> {

    // Buscar todos os telefones de um cliente (usuário)
    List<TelefoneCliente> findByUsuarioIdUsuario(Long idUsuario);

    // Buscar telefone por número (exato)
    List<TelefoneCliente> findByNumeroTelefone(String numeroTelefone);

    // Buscar telefone por número ignorando maiúsculas/minúsculas
    List<TelefoneCliente> findByNumeroTelefoneIgnoreCase(String numeroTelefone);

    // Verificar se um número de telefone já está cadastrado
    boolean existsByNumeroTelefone(String numeroTelefone);
}
