package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.TelefoneFornecedor;

@Repository
public interface TelefoneFornecedorRepository extends JpaRepository<TelefoneFornecedor, Long> {

    // Buscar telefones pelo id do fornecedor
    List<TelefoneFornecedor> findByFornecedorIdFornecedor(Long idFornecedor);

 // Buscar telefones pelo número (exato)
    List<TelefoneFornecedor> findByNumeroTelefoneFornecedor(String numeroTelefoneFornecedor);

    // Buscar telefones pelo número ignorando maiúsculas/minúsculas
    List<TelefoneFornecedor> findByNumeroTelefoneFornecedorIgnoreCase(String numeroTelefoneFornecedor);
}
