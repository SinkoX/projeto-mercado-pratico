package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque", nullable = false)
    private Long idEstoque;

    @OneToOne
    @JoinColumn(name = "id_produto", nullable = false, unique = true)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;

    protected Estoque() {}

    public Estoque(Produto produto, Integer quantidade, Integer quantidadeMinima) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo");
        if (quantidade == null || quantidade < 0) throw new IllegalArgumentException("Quantidade inválida");

        this.produto = produto;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima != null ? quantidadeMinima : 10;
    }

    // 🔹 Métodos de negócio
    public void adicionarQuantidade(Integer qtd) {
        if (qtd == null || qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        this.quantidade += qtd;
    }

    public void removerQuantidade(Integer qtd) {
        if (qtd == null || qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        if (this.quantidade < qtd) throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        this.quantidade -= qtd;
    }

    public boolean estoqueAbaixoDoMinimo() {
        return this.quantidade < this.quantidadeMinima;
    }

    // Getters e Setters
    public Long getIdEstoque() { return idEstoque; }
    public Produto getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }
    public Integer getQuantidadeMinima() { return quantidadeMinima; }

    public void setProduto(Produto produto) { this.produto = produto; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setQuantidadeMinima(Integer quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }
}