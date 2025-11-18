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

    @Column(name = "data_fornecimento", nullable = false)
    private LocalDateTime dataFornecimento = LocalDateTime.now();
    
    @Column(name = "valor_fornecimento", nullable = false)
    private double valorFornecimento;
    
    @OneToMany(mappedBy = "fornecimento", cascade = CascadeType.ALL)
    private List<ItemFornecimento> itens;
    
	public Long getIdFornecimento() {
		return idFornecimento;
	}

	public void setIdFornecimento(Long idFornecimento) {
		this.idFornecimento = idFornecimento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDateTime getDataFornecimento() {
		return dataFornecimento;
	}

	public void setDataFornecimento(LocalDateTime dataFornecimento) {
		this.dataFornecimento = dataFornecimento;
	}

	public double getValorFornecimento() {
		return valorFornecimento;
	}

	public void setValorFornecimento(double valorFornecimento) {
		this.valorFornecimento = valorFornecimento;
	}

	public List<ItemFornecimento> getItens() {
		return itens;
	}

	public void setItens(List<ItemFornecimento> itens) {
        this.itens = itens;
        if (itens != null) {
            itens.forEach(i -> i.setFornecimento(this));
        }
    }
}
