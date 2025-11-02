package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import com.senaidev.prjMercadoPratico.entities.ItemPedido;

public class ItemPedidoDTO {

    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal subTotal;

    public ItemPedidoDTO(ItemPedido item) {
        this.nomeProduto = item.getProduto().getNomeProduto();
        this.quantidade = item.getQuantidade();
        this.subTotal = item.getSubTotal();
    }

    // Getters
    public String getNomeProduto() { return nomeProduto; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getSubTotal() { return subTotal; }
}
