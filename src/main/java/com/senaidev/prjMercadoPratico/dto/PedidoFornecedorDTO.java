package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import com.senaidev.prjMercadoPratico.enums.StatusPedidoFornecedor;

public class PedidoFornecedorDTO {

    private Long idFornecedor;
    private String distribuidora;
    private BigDecimal valorTotal;
    private LocalDate dataPedido;
    private StatusPedidoFornecedor statusPedido;
    private List<ItemPedidoFornecedorDTO> itensPedidoFornecedor;

    public PedidoFornecedorDTO() {}

    public PedidoFornecedorDTO(PedidoFornecedor pedido) {
        this.idFornecedor = pedido.getFornecedor().getIdFornecedor();
        this.distribuidora = pedido.getDistribuidora();
        this.valorTotal = pedido.getValorTotal();
        this.dataPedido = pedido.getDataPedido();
        this.statusPedido = pedido.getStatusPedido();
        this.itensPedidoFornecedor = pedido.getItensPedidoFornecedor()
                .stream()
                .map(ItemPedidoFornecedorDTO::new)
                .toList();
    }

    // Getters e Setters
    public Long getIdFornecedor() { return idFornecedor; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public StatusPedidoFornecedor getStatusPedido() { return statusPedido; }
    public LocalDate getDataPedido() { return dataPedido; }
    public String getDistribuidora() { return distribuidora; }
    public List<ItemPedidoFornecedorDTO> getItens() { return itensPedidoFornecedor; }
	public void setIdFornecedor(Long idFornecedor) { this.idFornecedor = idFornecedor; }
	public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
	public void setStatusPedido(StatusPedidoFornecedor statusPedido) { this.statusPedido = statusPedido; }
	public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }
	public void setDistribuidora(String distribuidora) { this.distribuidora = distribuidora; }
	public void setItens(List<ItemPedidoFornecedorDTO> itens) { this.itensPedidoFornecedor = itens; }
}
