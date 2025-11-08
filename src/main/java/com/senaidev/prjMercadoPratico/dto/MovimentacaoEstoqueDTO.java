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

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final MovimentacaoEstoqueDTO dto = new MovimentacaoEstoqueDTO();

        public Builder idMovimentacao(Long id) { dto.idMovimentacao = id; return this; }
        public Builder idProduto(Long id) { dto.idProduto = id; return this; }
        public Builder nomeProduto(String nome) { dto.nomeProduto = nome; return this; }
        public Builder tipoMovimentacao(TipoMovimentacao tipo) { dto.tipoMovimentacao = tipo; return this; }
        public Builder quantidade(Integer quantidade) { dto.quantidade = quantidade; return this; }
        public Builder dataMovimentacao(LocalDateTime data) { dto.dataMovimentacao = data; return this; }
        public Builder idPedidoUsuario(Long id) { dto.idPedidoUsuario = id; return this; }
        public Builder idPedidoFornecedor(Long id) { dto.idPedidoFornecedor = id; return this; }
        public Builder observacao(String obs) { dto.observacao = obs; return this; }

        public MovimentacaoEstoqueDTO build() { return dto; }
    }

    // Converter Entity → DTO
    public static MovimentacaoEstoqueDTO fromEntity(MovimentacaoEstoque m) {
        return MovimentacaoEstoqueDTO.builder()
                .idMovimentacao(m.getIdMovimentacao())
                .idProduto(m.getProduto().getIdProduto())
                .nomeProduto(m.getProduto().getNomeProduto())
                .tipoMovimentacao(m.getTipoMovimentacao())
                .quantidade(m.getQuantidade())
                .dataMovimentacao(m.getDataMovimentacao())
                .idPedidoUsuario(m.getPedidoUsuario() != null ? m.getPedidoUsuario().getIdPedidoUsuario() : null)
                .idPedidoFornecedor(m.getPedidoFornecedor() != null ? m.getPedidoFornecedor().getIdPedidoFornecedor() : null)
                .observacao(m.getObservacao())
                .build();
    }

    // Getters
    public Long getIdMovimentacao() { return idMovimentacao; }
    public Long getIdProduto() { return idProduto; }
    public String getNomeProduto() { return nomeProduto; }
    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public Integer getQuantidade() { return quantidade; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public Long getIdPedidoUsuario() { return idPedidoUsuario; }
    public Long getIdPedidoFornecedor() { return idPedidoFornecedor; }
    public String getObservacao() { return observacao; }
}
