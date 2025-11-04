package com.senaidev.prjMercadoPratico.entities;

import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque", nullable = false)
    private Long idEstoque;

    @OneToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_atual", nullable = false)
    private Integer quantidadeAtual;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false, length = 10)
    private TipoMovimentacao tipoMovimentacao;

    // Construtor vazio
    protected Estoque() {}

    // Construtor completo
    public Estoque(Long idEstoque, Produto produto, Integer quantidadeAtual, TipoMovimentacao tipoMovimentacao) {
        this.idEstoque = idEstoque;
        this.produto = produto;
        this.quantidadeAtual = quantidadeAtual != null ? quantidadeAtual : 0;
        this.tipoMovimentacao = tipoMovimentacao != null ? tipoMovimentacao : TipoMovimentacao.ENTRADA;
    }

    // ----- Métodos de movimentação -----

    public void registrarEntrada(Integer quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida para entrada.");
        this.quantidadeAtual += quantidade;
        this.tipoMovimentacao = TipoMovimentacao.ENTRADA;
    }

    public void registrarSaida(Integer quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida para saída.");
        if (this.quantidadeAtual < quantidade)
            throw new IllegalStateException("Estoque insuficiente.");
        this.quantidadeAtual -= quantidade;
        this.tipoMovimentacao = TipoMovimentacao.SAIDA;
    }

    // ----- Getters e Setters -----

    public Long getIdEstoque() { return idEstoque; }
    public void setIdEstoque(Long idEstoque) { this.idEstoque = idEstoque; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidadeAtual() { return quantidadeAtual; }
    public void setQuantidadeAtual(Integer quantidadeAtual) { this.quantidadeAtual = quantidadeAtual; }

    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
}
