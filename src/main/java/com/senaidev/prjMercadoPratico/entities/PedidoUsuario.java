package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.senaidev.prjMercadoPratico.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false, length = 20)
    private StatusPedido statusPedido;

    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;
    
    @ManyToOne
    @JoinColumn(name = "id_Funcionario")
    private Funcionario funcionario;

    // 🔒 Construtor padrão exigido pelo JPA
    protected PedidoUsuario() {}

    // 🧱 Construtor principal — cria um pedido imutável
    public PedidoUsuario(Usuario usuario, List<ItemPedido> itensPedido) {
        if (usuario == null) throw new IllegalArgumentException("Usuário não pode ser nulo");
        if (itensPedido == null || itensPedido.isEmpty())
            throw new IllegalArgumentException("O pedido deve conter ao menos um item");

        this.usuario = usuario;
        this.itensPedido = List.copyOf(itensPedido);
        this.statusPedido = StatusPedido.PENDENTE;
        this.dataPedido = LocalDate.now();
        this.valorTotal = calcularValorTotal(itensPedido);

        // Vincula os itens ao pedido
        this.itensPedido.forEach(item -> item.setPedidoUsuario(this));
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .map(ItemPedido::getSubTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 🔍 Getters
    public Long getIdPedidoUsuario() { return idPedidoUsuario; }
    public Usuario getUsuario() { return usuario; }
    public List<ItemPedido> getItensPedido() { return Collections.unmodifiableList(itensPedido); }
    public BigDecimal getValorTotal() { return valorTotal; }
    public StatusPedido getStatusPedido() { return statusPedido; }
    public LocalDate getDataPedido() { return dataPedido; }
    public Funcionario getFuncionario() { return funcionario; }

    public void setIdPedidoUsuario(Long idPedidoUsuario) { this.idPedidoUsuario = idPedidoUsuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
	public void setItensPedido(List<ItemPedido> itensPedido) { this.itensPedido = itensPedido; }
	public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
	public void setStatusPedido(StatusPedido statusPedido) { this.statusPedido = statusPedido; }
	public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }
	public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

	// 🧭 Atualiza status (controle de fluxo)
    public void atualizarStatus(StatusPedido novoStatus) {
        if (novoStatus == null) throw new IllegalArgumentException("Status não pode ser nulo");

        if (this.statusPedido == StatusPedido.CANCELADO || this.statusPedido == StatusPedido.ENTREGUE) {
            throw new IllegalStateException("Pedido já finalizado não pode mudar de status");
        }

        this.statusPedido = novoStatus;
    }
}
