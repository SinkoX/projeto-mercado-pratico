package com.senaidev.prjMercadoPratico.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho", nullable = false)
    private Long idCarrinho;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "quantidade_total", nullable = false)
    private Integer quantidadeTotal = 0;

    // Construtores
    public Carrinho() {

    }

    public Carrinho(Long idCarrinho, Usuario usuario, List<ItemCarrinho> itensCarrinho, 
                    BigDecimal valorTotal, Integer quantidadeTotal) {
        super();
        this.idCarrinho = idCarrinho;
        this.usuario = usuario;
        this.itensCarrinho = itensCarrinho;
        this.valorTotal = valorTotal;
        this.quantidadeTotal = quantidadeTotal;
    }

    // Métodos de domínio
    public void adicionarItem(Produto produto, int quantidade) {
        ItemCarrinho existente = itensCarrinho.stream()
                .filter(i -> i.getProduto().getIdProduto().equals(produto.getIdProduto()))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setQuantidade(existente.getQuantidade() + quantidade);
            existente.setSubTotal(produto.getPrecoProduto()
                    .multiply(BigDecimal.valueOf(existente.getQuantidade())));
        } else {
            ItemCarrinho novoItem = new ItemCarrinho();
            novoItem.setCarrinho(this);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(quantidade);
            novoItem.setSubTotal(produto.getPrecoProduto().multiply(BigDecimal.valueOf(quantidade)));
            itensCarrinho.add(novoItem);
        }

        atualizarTotais();
    }

    public void removerItem(Long idProduto) {
        itensCarrinho.removeIf(item -> item.getProduto().getIdProduto().equals(idProduto));
        atualizarTotais();
    }

    public void atualizarTotais() {
        BigDecimal total = BigDecimal.ZERO;
        int qtdTotal = 0;

        for (ItemCarrinho item : itensCarrinho) {
            total = total.add(item.getSubTotal());
            qtdTotal += item.getQuantidade();
        }

        this.valorTotal = total;
        this.quantidadeTotal = qtdTotal;
    }

    // Getters e Setters
    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemCarrinho> getItensCarrinho() {
        return itensCarrinho;
    }

    public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
}
