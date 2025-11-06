package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.ItemPedidoFornecedor;

@Repository
public interface ItemPedidoFornecedorRepository extends JpaRepository<ItemPedidoFornecedor, Long> {

    // Busca itens de um pedido específico
    List<ItemPedidoFornecedor> findByPedidoFornecedorIdPedidoFornecedor(Long idPedidoFornecedor);

    // Busca itens por produto
    List<ItemPedidoFornecedor> findByProdutoIdProduto(Long idProduto);
}