package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_fornecimento")
public class ItemFornecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemFornecimento;

    @ManyToOne
    @JoinColumn(name = "id_fornecimento", nullable = false)
    private Fornecimento fornecimento;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    public Long getIdItemFornecimento() { return idItemFornecimento; }
    public Fornecimento getFornecimento() { return fornecimento; }
    public Produto getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }

    public void setFornecimento(Fornecimento fornecimento) { this.fornecimento = fornecimento; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
