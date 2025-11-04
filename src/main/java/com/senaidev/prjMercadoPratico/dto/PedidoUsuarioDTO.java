package com.senaidev.prjMercadoPratico.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.enums.StatusPedido;

public class PedidoUsuarioDTO {

    private Long idPedidoUsuario;
    private String nomeUsuario;
    private BigDecimal valorTotal;
    private BigDecimal frete;
    private BigDecimal desconto;
    private BigDecimal valorFinal;
    private StatusPedido statusPedido;
    private LocalDate dataPedido;
    private List<ItemPedidoDTO> itens;

    public PedidoUsuarioDTO(PedidoUsuario pedido) {
        this.idPedidoUsuario = pedido.getIdPedidoUsuario();
        this.nomeUsuario = pedido.getUsuario().getNomeUsuario();
        this.valorTotal = pedido.getValorTotal();
        this.frete = pedido.getFrete();           // 🔹 agora incluído
        this.desconto = pedido.getDesconto();     // 🔹 agora incluído
        this.valorFinal = pedido.getValorFinal(); // 🔹 agora incluído
        this.statusPedido = pedido.getStatusPedido();
        this.dataPedido = pedido.getDataPedido();
        this.itens = pedido.getItensPedido().stream()
                .map(ItemPedidoDTO::new)
                .collect(Collectors.toList());
    }

    // Getters
    public Long getIdPedidoUsuario() { return idPedidoUsuario; }
    public String getNomeUsuario() { return nomeUsuario; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public BigDecimal getFrete() { return frete; }
    public BigDecimal getDesconto() { return desconto; }
    public BigDecimal getValorFinal() { return valorFinal; }
    public StatusPedido getStatusPedido() { return statusPedido; }
    public LocalDate getDataPedido() { return dataPedido; }
    public List<ItemPedidoDTO> getItens() { return itens; }
}
