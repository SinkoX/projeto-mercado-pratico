package com.senaidev.prjMercadoPratico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.ItemPedidoFornecedor;

@Repository
public interface ItemPedidoFornecedorRepository extends JpaRepository<ItemPedidoFornecedor, Long> {
}
