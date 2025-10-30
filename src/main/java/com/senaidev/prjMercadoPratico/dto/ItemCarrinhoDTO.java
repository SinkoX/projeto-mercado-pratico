package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;

import com.senaidev.prjMercadoPratico.entities.ItemCarrinho;

public class ItemCarrinhoDTO {
    
    private Long idItemCarrinho;
    private Long idProduto;       
    private String nomeProduto;
    private String imgUrl;        
    private Integer quantidade;
    private BigDecimal subTotal;

    public ItemCarrinhoDTO(ItemCarrinho item) {
        this.idItemCarrinho = item.getIdItemCarrinho();
        this.idProduto = item.getProduto().getIdProduto();      
        this.nomeProduto = item.getProduto().getNomeProduto();
        this.imgUrl = item.getProduto().getImgUrl();            
        this.quantidade = item.getQuantidade();
        this.subTotal = item.getSubTotal();
    }

    // Getters
    public Long getIdItemCarrinho() { return idItemCarrinho; }
    public Long getIdProduto() { return idProduto; }
    public String getNomeProduto() { return nomeProduto; }
    public String getImgUrl() { return imgUrl; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getSubTotal() { return subTotal; }
}
