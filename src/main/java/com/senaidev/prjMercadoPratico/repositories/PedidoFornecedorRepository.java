package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;

@Repository
public interface PedidoFornecedorRepository extends JpaRepository<PedidoFornecedor, Long> {
    List<PedidoFornecedor> findByFornecedorIdFornecedor(Long idFornecedor);
}
