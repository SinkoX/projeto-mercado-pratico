package com.senaidev.prjMercadoPratico.dto;

import com.senaidev.prjMercadoPratico.entities.Estoque;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;

public class EstoqueDTO {

    private Long idEstoque;
    private Long idProduto;
    private Integer quantidadeAtual;
    private TipoMovimentacao tipoMovimentacao;

    public EstoqueDTO(Estoque estoque) {
        this.idEstoque = estoque.getIdEstoque();
        this.idProduto = estoque.getProduto().getIdProduto();
        this.quantidadeAtual = estoque.getQuantidadeAtual();
        this.tipoMovimentacao = estoque.getTipoMovimentacao();
    }

    // Getters e Setters
    public Long getIdEstoque() { return idEstoque; }
    public Long getIdProduto() { return idProduto; }
    public Integer getQuantidadeAtual() { return quantidadeAtual; }
    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
	public void setIdEstoque(Long idEstoque) { this.idEstoque = idEstoque; }
	public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }
	public void setQuantidadeAtual(Integer quantidadeAtual) { this.quantidadeAtual = quantidadeAtual; }
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
}
