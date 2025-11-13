package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_fornecimento")
public class Fornecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornecimento;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    @Column(nullable = false)
    private LocalDateTime dataFornecimento = LocalDateTime.now();

    @OneToMany(mappedBy = "fornecimento", cascade = CascadeType.ALL)
    private List<ItemFornecimento> itens;

    public Long getIdFornecimento() { return idFornecimento; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public LocalDateTime getDataFornecimento() { return dataFornecimento; }
    public List<ItemFornecimento> getItens() { return itens; }

    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
    public void setItens(List<ItemFornecimento> itens) {
        this.itens = itens;
        if (itens != null) {
            itens.forEach(i -> i.setFornecimento(this));
        }
    }
}
