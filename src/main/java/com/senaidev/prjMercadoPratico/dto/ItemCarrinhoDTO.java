package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;

import com.senaidev.prjMercadoPratico.entities.ItemCarrinho;

public class ItemCarrinhoDTO {
    
    private Long idItemCarrinho;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal subTotal;

    public ItemCarrinhoDTO(ItemCarrinho item) {
        this.idItemCarrinho = item.getIdItemCarrinho();
        this.nomeProduto = item.getProduto().getNomeProduto();
        this.quantidade = item.getQuantidade();
        this.subTotal = item.getSubTotal();
    }

    // Getters
    public Long getIdItemCarrinho() {
        return idItemCarrinho;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}

