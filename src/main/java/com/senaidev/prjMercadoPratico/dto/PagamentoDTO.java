package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.util.List;

public class PagamentoDTO {

    private Long idEnderecoEntrega;
    private BigDecimal frete;
    private BigDecimal desconto;

    // Lista de itens do pedido
    private List<ItemPagamentoDTO> itens;

    // Getters e Setters
    public Long getIdEnderecoEntrega() { return idEnderecoEntrega; }
    public void setIdEnderecoEntrega(Long idEnderecoEntrega) { this.idEnderecoEntrega = idEnderecoEntrega; }

    public BigDecimal getFrete() { return frete; }
    public void setFrete(BigDecimal frete) { this.frete = frete; }

    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }

    public List<ItemPagamentoDTO> getItens() { return itens; }
    public void setItens(List<ItemPagamentoDTO> itens) { this.itens = itens; }
}
