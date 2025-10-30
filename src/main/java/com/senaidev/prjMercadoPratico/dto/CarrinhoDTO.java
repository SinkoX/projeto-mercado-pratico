package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.senaidev.prjMercadoPratico.entities.Carrinho;

public class CarrinhoDTO {

    private Long idCarrinho;
    private String nomeUsuario;
    private BigDecimal valorTotal;
    private Integer quantidadeTotal;
    private List<ItemCarrinhoDTO> itens;

    public CarrinhoDTO(Carrinho carrinho) {
        this.idCarrinho = carrinho.getIdCarrinho();
        this.nomeUsuario = carrinho.getUsuario().getNomeUsuario();
        this.valorTotal = carrinho.getValorTotal();
        this.quantidadeTotal = carrinho.getQuantidadeTotal();
        this.itens = carrinho.getItensCarrinho() != null
            ? carrinho.getItensCarrinho()
                      .stream()
                      .map(ItemCarrinhoDTO::new) // usa o DTO externo
                      .collect(Collectors.toList())
            : List.of();
    }

    public Long getIdCarrinho() { return idCarrinho; }
    public String getNomeUsuario() { return nomeUsuario; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public List<ItemCarrinhoDTO> getItens() { return itens; }
}
