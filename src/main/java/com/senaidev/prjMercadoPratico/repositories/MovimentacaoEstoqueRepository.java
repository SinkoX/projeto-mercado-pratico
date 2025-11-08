package com.senaidev.prjMercadoPratico.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.MovimentacaoEstoque;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    // Busca movimentações por produto
    List<MovimentacaoEstoque> findByProdutoIdProduto(Long idProduto);

    // Busca movimentações por tipo (ENTRADA ou SAIDA)
    List<MovimentacaoEstoque> findByTipoMovimentacao(TipoMovimentacao tipo);

    // Busca movimentações por período
    @Query("SELECT m FROM MovimentacaoEstoque m WHERE m.dataMovimentacao BETWEEN :dataInicio AND :dataFim")
    List<MovimentacaoEstoque> findByPeriodo(@Param("dataInicio") LocalDateTime dataInicio, 
                                            @Param("dataFim") LocalDateTime dataFim);

    // Busca movimentações por PedidoUsuario
    List<MovimentacaoEstoque> findByPedidoUsuarioIdPedidoUsuario(Long idPedidoUsuario);

    // Busca movimentações por PedidoFornecedor
    List<MovimentacaoEstoque> findByPedidoFornecedorIdPedidoFornecedor(Long idPedidoFornecedor);

    // Busca últimas movimentações (limitado)
    @Query("SELECT m FROM MovimentacaoEstoque m ORDER BY m.dataMovimentacao DESC")
    List<MovimentacaoEstoque> findUltimasMovimentacoes();
}