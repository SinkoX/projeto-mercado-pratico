package com.senaidev.prjMercadoPratico.dto;

import com.senaidev.prjMercadoPratico.entities.Estoque;

public class EstoqueDTO {

    private Long idEstoque;
    private Long idProduto;
    private String nomeProduto;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private boolean estoqueAbaixoDoMinimo;

    public EstoqueDTO() {}

    public EstoqueDTO(Estoque estoque) {
        this.idEstoque = estoque.getIdEstoque();
        this.idProduto = estoque.getProduto().getIdProduto();
        this.nomeProduto = estoque.getProduto().getNomeProduto();
        this.quantidade = estoque.getQuantidade();
        this.quantidadeMinima = estoque.getQuantidadeMinima();
        this.estoqueAbaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();
    }

    // Getters e Setters
    public Long getIdEstoque() { return idEstoque; }
    public void setIdEstoque(Long idEstoque) { this.idEstoque = idEstoque; }

    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Integer getQuantidadeMinima() { return quantidadeMinima; }
    public void setQuantidadeMinima(Integer quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }

    public boolean isEstoqueAbaixoDoMinimo() { return estoqueAbaixoDoMinimo; }
    public void setEstoqueAbaixoDoMinimo(boolean estoqueAbaixoDoMinimo) { this.estoqueAbaixoDoMinimo = estoqueAbaixoDoMinimo; }
}