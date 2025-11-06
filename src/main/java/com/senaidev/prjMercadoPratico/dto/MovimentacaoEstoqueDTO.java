package com.senaidev.prjMercadoPratico.dto;

import java.time.LocalDateTime;

import com.senaidev.prjMercadoPratico.entities.MovimentacaoEstoque;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;

public class MovimentacaoEstoqueDTO {

    private Long idMovimentacao;
    private Long idProduto;
    private String nomeProduto;
    private TipoMovimentacao tipoMovimentacao;
    private Integer quantidade;
    private LocalDateTime dataMovimentacao;
    private Long idPedidoUsuario;
    private Long idPedidoFornecedor;
    private String observacao;

    public MovimentacaoEstoqueDTO() {}

    public MovimentacaoEstoqueDTO(MovimentacaoEstoque movimentacao) {
        this.idMovimentacao = movimentacao.getIdMovimentacao();
        this.idProduto = movimentacao.getProduto().getIdProduto();
        this.nomeProduto = movimentacao.getProduto().getNomeProduto();
        this.tipoMovimentacao = movimentacao.getTipoMovimentacao();
        this.quantidade = movimentacao.getQuantidade();
        this.dataMovimentacao = movimentacao.getDataMovimentacao();
        this.idPedidoUsuario = movimentacao.getPedidoUsuario() != null ? 
                               movimentacao.getPedidoUsuario().getIdPedidoUsuario() : null;
        this.idPedidoFornecedor = movimentacao.getPedidoFornecedor() != null ? 
                                  movimentacao.getPedidoFornecedor().getIdPedidoFornecedor() : null;
        this.observacao = movimentacao.getObservacao();
    }

    // Getters e Setters
    public Long getIdMovimentacao() { return idMovimentacao; }
    public void setIdMovimentacao(Long idMovimentacao) { this.idMovimentacao = idMovimentacao; }

    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public Long getIdPedidoUsuario() { return idPedidoUsuario; }
    public void setIdPedidoUsuario(Long idPedidoUsuario) { this.idPedidoUsuario = idPedidoUsuario; }

    public Long getIdPedidoFornecedor() { return idPedidoFornecedor; }
    public void setIdPedidoFornecedor(Long idPedidoFornecedor) { this.idPedidoFornecedor = idPedidoFornecedor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}