package com.senaidev.prjMercadoPratico.entities;

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
    private Long idTelefone;

    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    // Construtor padrão
    public TelefoneFornecedor() {
    }

    // Construtor com todos os campos
    public TelefoneFornecedor(Long idTelefone, String numeroTelefone, Fornecedor fornecedor) {
        this.idTelefone = idTelefone;
        this.numeroTelefone = numeroTelefone;
        this.fornecedor = fornecedor;
    }

    // Getters & Setters

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
