package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDateTime;

import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_movimentacao_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao", nullable = false)
    private Long idMovimentacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estoque", nullable = false)
    private Estoque estoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false, length = 10)
    private TipoMovimentacao tipoMovimentacao;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "id_pedido_usuario")
    private PedidoUsuario pedidoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_pedido_fornecedor")
    private PedidoFornecedor pedidoFornecedor;

    @Column(name = "observacao", length = 500)
    private String observacao;

    protected MovimentacaoEstoque() {}

    // Construtor para SAÍDA (PedidoUsuario)
    public MovimentacaoEstoque(Produto produto, Estoque estoque, Integer quantidade, 
                               PedidoUsuario pedidoUsuario, String observacao) {
        validarCampos(produto, estoque, quantidade);
        
        this.produto = produto;
        this.estoque = estoque;
        this.quantidade = quantidade;
        this.tipoMovimentacao = TipoMovimentacao.SAIDA;
        this.pedidoUsuario = pedidoUsuario;
        this.dataMovimentacao = LocalDateTime.now();
        this.observacao = observacao;
    }

    // Construtor para ENTRADA (PedidoFornecedor)
    public MovimentacaoEstoque(Produto produto, Estoque estoque, Integer quantidade, 
                               PedidoFornecedor pedidoFornecedor, String observacao) {
        validarCampos(produto, estoque, quantidade);
        
        this.produto = produto;
        this.estoque = estoque;
        this.quantidade = quantidade;
        this.tipoMovimentacao = TipoMovimentacao.ENTRADA;
        this.pedidoFornecedor = pedidoFornecedor;
        this.dataMovimentacao = LocalDateTime.now();
        this.observacao = observacao;
    }

    private void validarCampos(Produto produto, Estoque estoque, Integer quantidade) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo");
        if (estoque == null) throw new IllegalArgumentException("Estoque não pode ser nulo");
        if (quantidade == null || quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida");
    }

    // Getters e Setters
    public Long getIdMovimentacao() { return idMovimentacao; }
    public Produto getProduto() { return produto; }
    public Estoque getEstoque() { return estoque; }
    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public Integer getQuantidade() { return quantidade; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public PedidoUsuario getPedidoUsuario() { return pedidoUsuario; }
    public PedidoFornecedor getPedidoFornecedor() { return pedidoFornecedor; }
    public String getObservacao() { return observacao; }

    public void setProduto(Produto produto) { this.produto = produto; }
    public void setEstoque(Estoque estoque) { this.estoque = estoque; }
    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }
    public void setPedidoUsuario(PedidoUsuario pedidoUsuario) { this.pedidoUsuario = pedidoUsuario; }
    public void setPedidoFornecedor(PedidoFornecedor pedidoFornecedor) { this.pedidoFornecedor = pedidoFornecedor; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}