package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.TelefoneFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneFornecedorRepository extends JpaRepository<TelefoneFornecedor, Long> {

    // Buscar telefones pelo id do fornecedor
    List<TelefoneFornecedor> findByFornecedorIdFornecedor(Long idFornecedor);

 // Buscar telefones pelo número (exato)
    List<TelefoneFornecedor> findByNumeroTelefoneFornecedor(String numeroTelefoneFornecedor);

    // Buscar telefones pelo número ignorando maiúsculas/minúsculas
    List<TelefoneFornecedor> findByNumeroTelefoneFornecedorIgnoreCase(String numeroTelefoneFornecedor);
}
