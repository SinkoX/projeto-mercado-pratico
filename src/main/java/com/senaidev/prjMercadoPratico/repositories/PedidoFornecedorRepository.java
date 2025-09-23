package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoFornecedorRepository extends JpaRepository<PedidoFornecedor, Long> {

    // Buscar pedidos por ID do fornecedor
    List<PedidoFornecedor> findByFornecedorIdFornecedor(Long idFornecedor);

    // Buscar pedidos por ID do produto
    List<PedidoFornecedor> findByProdutoIdProduto(Long idProduto);

    // Buscar pedidos feitos em uma data específica
    List<PedidoFornecedor> findByDataPedidoFornecedor(LocalDate dataPedidoFornecedor);
    
}
