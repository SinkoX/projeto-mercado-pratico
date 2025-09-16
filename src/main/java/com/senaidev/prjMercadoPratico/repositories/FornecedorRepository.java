package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    // Buscar por nome (exato)
    List<Fornecedor> findByNomeFornecedor(String nomeFornecedor);

    // Buscar por nome contendo (ignora maiúsculas/minúsculas)
    List<Fornecedor> findByNomeFornecedorContainingIgnoreCase(String nomeFornecedor);

    // Buscar por email
    Optional<Fornecedor> findByEmailFornecedor(String emailFornecedor);

    // Verificar existência por CPF
    boolean existsByCpfFornecedor(String cpfFornecedor);

    // Verificar existência por CNPJ
    boolean existsByCnpj(String cnpj);

    // Buscar por CNPJ ou CPF
    Optional<Fornecedor> findByCpfFornecedorOrCnpj(String cpf, String cnpj);
}
