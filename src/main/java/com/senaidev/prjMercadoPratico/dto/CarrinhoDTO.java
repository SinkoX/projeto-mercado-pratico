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
        this.itens = carrinho.getItensCarrinho()
                             .stream()
                             .map(ItemCarrinhoDTO::new)
                             .collect(Collectors.toList());
    }

    // Getters
    public Long getIdCarrinho() { return idCarrinho; }
    public String getNomeUsuario() { return nomeUsuario; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public List<ItemCarrinhoDTO> getItens() { return itens; }

    // DTO do item
    public static class ItemCarrinhoDTO {
        private Long idItemCarrinho;
        private Long idProduto;       // ⚠️ Adicionado
        private String nomeProduto;
        private Integer quantidade;
        private BigDecimal subTotal;

        public ItemCarrinhoDTO(com.senaidev.prjMercadoPratico.entities.ItemCarrinho item) {
            this.idItemCarrinho = item.getIdItemCarrinho();
            this.idProduto = item.getProduto().getIdProduto(); // ⚠️ Aqui
            this.nomeProduto = item.getProduto().getNomeProduto();
            this.quantidade = item.getQuantidade();
            this.subTotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
        }

        // Getters
        public Long getIdItemCarrinho() { return idItemCarrinho; }
        public Long getIdProduto() { return idProduto; }
        public String getNomeProduto() { return nomeProduto; }
        public Integer getQuantidade() { return quantidade; }
        public BigDecimal getSubTotal() { return subTotal; }
    }
}
