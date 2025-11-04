package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;

import com.senaidev.prjMercadoPratico.entities.ItemPedidoFornecedor;

public class ItemPedidoFornecedorDTO {

    private Long id;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal subTotal;

    public ItemPedidoFornecedorDTO(ItemPedidoFornecedor item) {
        this.id = item.getIdItemPedidoFornecedor();
        this.nomeProduto = item.getProduto().getNomeProduto();
        this.quantidade = item.getQuantidade();
        this.subTotal = item.getSubTotal();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getNomeProduto() { return nomeProduto; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getSubTotal() { return subTotal; }
	public void setId(Long id) { this.id = id; }
	public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
	public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
	public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
}
