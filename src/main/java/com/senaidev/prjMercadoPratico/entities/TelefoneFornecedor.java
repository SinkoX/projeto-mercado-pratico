package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_telefone_fornecedor")
public class TelefoneFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Telefone_Fornecedor", nullable = false)
    private Long idTelefoneFornecedor;

    @Column(name = "numero_Telefone_Fornecedor", nullable = false, length = 15)
    private String numeroTelefoneFornecedor;

    @ManyToOne
    @JoinColumn(name = "id_Fornecedor")
    private Fornecedor fornecedor;

    // Construtor padrão
    public TelefoneFornecedor() {
    }

    // Construtor com todos os campos
    public TelefoneFornecedor(Long idTelefoneFornecedor, String numeroTelefoneFornecedor, Fornecedor fornecedor) {
        this.idTelefoneFornecedor = idTelefoneFornecedor;
        this.numeroTelefoneFornecedor = numeroTelefoneFornecedor;
        this.fornecedor = fornecedor;
    }

    // Getters & Setters

    public Long getIdTelefoneFornecedor() {
        return idTelefoneFornecedor;
    }

    public void setIdTelefoneFornecedor(Long idTelefoneFornecedor) {
        this.idTelefoneFornecedor = idTelefoneFornecedor;
    }

    public String getNumeroTelefoneFornecedor() {
        return numeroTelefoneFornecedor;
    }

    public void setNumeroTelefoneFornecedor(String numeroTelefoneFornecedor) {
        this.numeroTelefoneFornecedor = numeroTelefoneFornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
