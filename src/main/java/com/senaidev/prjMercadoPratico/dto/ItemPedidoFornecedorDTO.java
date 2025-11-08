package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;

import com.senaidev.prjMercadoPratico.entities.ItemPedidoFornecedor;

public class ItemPedidoFornecedorDTO {

    private Long idItemPedidoFornecedor;
    private Long idProduto;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subTotal;

    public ItemPedidoFornecedorDTO() {}

    public ItemPedidoFornecedorDTO(ItemPedidoFornecedor item) {
        this.idItemPedidoFornecedor = item.getIdItemPedidoFornecedor();
        this.idProduto = item.getProduto().getIdProduto();
        this.nomeProduto = item.getProduto().getNomeProduto();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.subTotal = item.getSubTotal();
    }

    // Getters e Setters
    public Long getIdItemPedidoFornecedor() { return idItemPedidoFornecedor; }
    public void setIdItemPedidoFornecedor(Long idItemPedidoFornecedor) { this.idItemPedidoFornecedor = idItemPedidoFornecedor; }

    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
}