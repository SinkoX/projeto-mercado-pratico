package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;

@Entity
@Table(name = "movimentacao_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimentacao;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;

    private Integer quantidade;
    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "pedido_usuario_id")
    private PedidoUsuario pedidoUsuario;

    @ManyToOne
    @JoinColumn(name = "pedido_fornecedor_id")
    private PedidoFornecedor pedidoFornecedor;

    private String observacao;

    protected MovimentacaoEstoque() {}

    // Factory method para ENTRADA automática
    public static MovimentacaoEstoque criarEntrada(Produto produto, Estoque estoque, Integer quantidade,
                                                   PedidoFornecedor pedidoFornecedor, String observacao) {
        MovimentacaoEstoque m = new MovimentacaoEstoque();
        m.produto = produto;
        m.estoque = estoque;
        m.quantidade = quantidade;
        m.tipoMovimentacao = TipoMovimentacao.ENTRADA;
        m.pedidoFornecedor = pedidoFornecedor;
        m.dataMovimentacao = LocalDateTime.now();
        m.observacao = observacao;
        return m;
    }

    // Factory method para SAÍDA automática
    public static MovimentacaoEstoque criarSaida(Produto produto, Estoque estoque, Integer quantidade,
                                                 PedidoUsuario pedidoUsuario, String observacao) {
        MovimentacaoEstoque m = new MovimentacaoEstoque();
        m.produto = produto;
        m.estoque = estoque;
        m.quantidade = quantidade;
        m.tipoMovimentacao = TipoMovimentacao.SAIDA;
        m.pedidoUsuario = pedidoUsuario;
        m.dataMovimentacao = LocalDateTime.now();
        m.observacao = observacao;
        return m;
    }

    // Factory method para movimentação manual
    public static MovimentacaoEstoque criarManual(Produto produto, Estoque estoque, Integer quantidade,
                                                  TipoMovimentacao tipo, String observacao) {
        MovimentacaoEstoque m = new MovimentacaoEstoque();
        m.produto = produto;
        m.estoque = estoque;
        m.quantidade = quantidade;
        m.tipoMovimentacao = tipo;
        m.dataMovimentacao = LocalDateTime.now();
        m.observacao = observacao;
        return m;
    }

    // Getters
    public Long getIdMovimentacao() { return idMovimentacao; }
    public Produto getProduto() { return produto; }
    public Estoque getEstoque() { return estoque; }
    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public Integer getQuantidade() { return quantidade; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public PedidoUsuario getPedidoUsuario() { return pedidoUsuario; }
    public PedidoFornecedor getPedidoFornecedor() { return pedidoFornecedor; }
    public String getObservacao() { return observacao; }
}
