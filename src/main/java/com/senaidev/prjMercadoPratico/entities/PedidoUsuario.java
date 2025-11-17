package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.senaidev.prjMercadoPratico.enums.StatusPedido;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido_usuario")
public class PedidoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_usuario", nullable = false)
    private Long idPedidoUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pedidoUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "frete", precision = 10, scale = 2)
    private BigDecimal frete;

    @Column(name = "desconto", precision = 10, scale = 2)
    private BigDecimal desconto;

    @Column(name = "valor_final", precision = 10, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "payment_intent")
    private String paymentIntent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false, length = 20)
    private StatusPedido statusPedido;

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoEntrega;

    protected PedidoUsuario() {}

    public PedidoUsuario(Usuario usuario, List<ItemPedido> itensPedido,
                         BigDecimal frete, BigDecimal desconto, Endereco enderecoEntrega) {
        if (usuario == null) throw new IllegalArgumentException("Usuário não pode ser nulo");
        if (itensPedido == null || itensPedido.isEmpty())
            throw new IllegalArgumentException("O pedido deve conter ao menos um item");

        this.usuario = usuario;
        this.itensPedido = List.copyOf(itensPedido);
        this.statusPedido = StatusPedido.CANCELADO;
        this.dataPedido = LocalDate.now();
        this.frete = frete != null ? frete : BigDecimal.ZERO;
        this.desconto = desconto != null ? desconto : BigDecimal.ZERO;
        this.enderecoEntrega = enderecoEntrega;

        this.valorTotal = calcularValorTotal(itensPedido);
        this.valorFinal = valorTotal.add(this.frete).subtract(this.desconto);

        this.itensPedido.forEach(item -> item.setPedidoUsuario(this));
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .map(ItemPedido::getSubTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 🔍 Getters e Setters
    public Long getIdPedidoUsuario() { return idPedidoUsuario; }
    public Usuario getUsuario() { return usuario; }
    public List<ItemPedido> getItensPedido() { return Collections.unmodifiableList(itensPedido); }
    public BigDecimal getValorTotal() { return valorTotal; }
    public BigDecimal getFrete() { return frete; }
    public BigDecimal getDesconto() { return desconto; }
    public BigDecimal getValorFinal() { return valorFinal; }
    public StatusPedido getStatusPedido() { return statusPedido; }
    public LocalDate getDataPedido() { return dataPedido; }
    public Funcionario getFuncionario() { return funcionario; }
    public Endereco getEnderecoEntrega() { return enderecoEntrega; }
    public String getPaymentIntent() { return paymentIntent; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setItensPedido(List<ItemPedido> itensPedido) { this.itensPedido = itensPedido; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public void setFrete(BigDecimal frete) { this.frete = frete; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }
    public void setValorFinal(BigDecimal valorFinal) { this.valorFinal = valorFinal; }
    public void setStatusPedido(StatusPedido statusPedido) { this.statusPedido = statusPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public void setEnderecoEntrega(Endereco enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
    public void setPaymentIntent(String paymentIntent) { this.paymentIntent = paymentIntent; }

    // Atualiza status
    public void atualizarStatus(StatusPedido novoStatus) {
        if (novoStatus == null) throw new IllegalArgumentException("Status não pode ser nulo");
        if (this.statusPedido == StatusPedido.CANCELADO || this.statusPedido == StatusPedido.PAGO) {
            throw new IllegalStateException("Pedido já finalizado não pode mudar de status");
        }
        this.statusPedido = novoStatus;
    }
}
