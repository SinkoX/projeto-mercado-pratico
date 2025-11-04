package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.senaidev.prjMercadoPratico.enums.StatusPedidoFornecedor;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido_fornecedor")
public class PedidoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_fornecedor", nullable = false)
    private Long idPedidoFornecedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "pedidoFornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoFornecedor> itensPedidoFornecedor;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "distribuidora")
    private String distribuidora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false, length = 20)
    private StatusPedidoFornecedor statusPedido;

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    protected PedidoFornecedor() {}

    public PedidoFornecedor(Fornecedor fornecedor, List<ItemPedidoFornecedor> itensPedidoFornecedor,
                            String distribuidora) {
        if (fornecedor == null) throw new IllegalArgumentException("Fornecedor não pode ser nulo");
        if (itensPedidoFornecedor == null || itensPedidoFornecedor.isEmpty())
            throw new IllegalArgumentException("O pedido deve conter ao menos um item");

        this.fornecedor = fornecedor;
        this.itensPedidoFornecedor = List.copyOf(itensPedidoFornecedor);
        this.distribuidora = distribuidora;
        this.statusPedido = StatusPedidoFornecedor.PENDENTE;
        this.dataPedido = LocalDate.now();

        this.valorTotal = calcularValorTotal(itensPedidoFornecedor);
        this.itensPedidoFornecedor.forEach(item -> item.setPedidoFornecedor(this));
    }

    private BigDecimal calcularValorTotal(List<ItemPedidoFornecedor> itens) {
        return itens.stream()
                .map(ItemPedidoFornecedor::getSubTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters e Setters
    public Long getIdPedidoFornecedor() { return idPedidoFornecedor; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public List<ItemPedidoFornecedor> getItensPedidoFornecedor() { return Collections.unmodifiableList(itensPedidoFornecedor); }
    public BigDecimal getValorTotal() { return valorTotal; }
    public String getDistribuidora() { return distribuidora; }
    public StatusPedidoFornecedor getStatusPedido() { return statusPedido; }
    public LocalDate getDataPedido() { return dataPedido; }

    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public void setItensPedidoFornecedor(List<ItemPedidoFornecedor> itensPedidoFornecedor) { this.itensPedidoFornecedor = itensPedidoFornecedor; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public void setDistribuidora(String distribuidora) { this.distribuidora = distribuidora; }
    public void setStatusPedido(StatusPedidoFornecedor statusPedido) { this.statusPedido = statusPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }

    public void atualizarStatus(StatusPedidoFornecedor novoStatus) {
        if (novoStatus == null) throw new IllegalArgumentException("Status não pode ser nulo");
        if (this.statusPedido == StatusPedidoFornecedor.CANCELADO || this.statusPedido == StatusPedidoFornecedor.RECEBIDO) {
            throw new IllegalStateException("Pedido já finalizado não pode mudar de status");
        }
        this.statusPedido = novoStatus;
    }
}
