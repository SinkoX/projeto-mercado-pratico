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
@Table(name = "tb_telefone_funcionario")
public class TelefoneFuncionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Telefone_Funcionario")
    private Long idTelefoneFuncionario;

    @Column(name = "numero_Telefone_Funcionario", nullable = false, length = 15)
    private String numeroTelefoneFuncionario;

    @ManyToOne
    @JoinColumn(name = "id_Funcionario")
    private Funcionario funcionario;

    // Construtor padrão
    public TelefoneFuncionario() {
    }

    // Construtor completo
    public TelefoneFuncionario(Long idTelefoneFuncionario, String numeroTelefoneFuncionario, Funcionario funcionario) {
        this.idTelefoneFuncionario = idTelefoneFuncionario;
        this.numeroTelefoneFuncionario = numeroTelefoneFuncionario;
        this.funcionario = funcionario;
    }

    // Getters & Setters

    public Long getIdTelefoneFuncionario() {
        return idTelefoneFuncionario;
    }

    public void setIdTelefoneFuncionario(Long idTelefoneFuncionario) {
        this.idTelefoneFuncionario = idTelefoneFuncionario;
    }

    public String getNumeroTelefoneFuncionario() {
        return numeroTelefoneFuncionario;
    }

    public void setNumeroTelefoneFuncionario(String numeroTelefoneFuncionario) {
        this.numeroTelefoneFuncionario = numeroTelefoneFuncionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
