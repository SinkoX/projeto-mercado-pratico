package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoFornecedorRepository extends JpaRepository<PedidoFornecedor, Long> {

    // 🔍 Buscar pedidos por ID do fornecedor
    List<PedidoFornecedor> findByFornecedorIdFornecedor(Long idFornecedor);

    // 🔍 Buscar pedidos por ID do produto
    List<PedidoFornecedor> findByProdutoIdProduto(Long idProduto);

    // 🔍 Buscar pedidos feitos em uma data específica
    List<PedidoFornecedor> findByDataPedido(LocalDate dataPedido);

    // 🔍 Buscar pedidos feitos antes de uma data
    List<PedidoFornecedor> findByDataPedidoBefore(LocalDate dataLimite);

    // 🔍 Buscar pedidos feitos depois de uma data
    List<PedidoFornecedor> findByDataPedidoAfter(LocalDate dataInicio);

    // 🔍 Buscar pedidos por faixa de datas
    List<PedidoFornecedor> findByDataPedidoBetween(LocalDate inicio, LocalDate fim);
}
