package com.senaidev.prjMercadoPratico.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;

    @Column(name = "nome_funcionario", nullable = false, length = 100)
    private String nomeFuncionario;

    @Column(name = "email_funcionario", nullable = false, unique = true, length = 50)
    private String emailFuncionario;

    @Column(name = "cpf_funcionario", nullable = false, unique = true, length = 11)
    private String cpfFuncionario;

    @Column(name = "senha_funcionario", nullable = false, length = 255)
    private String senhaFuncionario;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<TelefoneFuncionario> telefones;

    // 🔹 Construtor padrão
    public Funcionario() {
    }

    // 🔹 Construtor com todos os campos
    public Funcionario(Long idFuncionario, String nomeFuncionario, String emailFuncionario, String cpfFuncionario,
                       String senhaFuncionario, List<TelefoneFuncionario> telefones) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.cpfFuncionario = cpfFuncionario;
        this.senhaFuncionario = senhaFuncionario;
        this.telefones = telefones;
    }

    // 🔹 Getters & Setters

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public List<TelefoneFuncionario> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneFuncionario> telefones) {
        this.telefones = telefones;
    }
}
