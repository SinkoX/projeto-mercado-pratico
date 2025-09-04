package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "email_cliente", nullable = false, unique = true, length = 50)
    private String emailCliente;

    @Column(name = "cpf_cliente", nullable = false, unique = true, length = 11)
    private String cpfCliente;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "senha_cliente", nullable = false, length = 255)
    private String senhaCliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "idEndereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TelefoneCliente> telefones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoCliente> pedidos;

    // 🔹 Construtor padrão
    public Cliente() {
    }

    // 🔹 Construtor com todos os atributos
    public Cliente(Long idCliente, String nomeCliente, String emailCliente, String cpfCliente,
                   LocalDate dataNascimento, String senhaCliente, Endereco endereco,
                   List<TelefoneCliente> telefones, List<PedidoCliente> pedidos) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.cpfCliente = cpfCliente;
        this.dataNascimento = dataNascimento;
        this.senhaCliente = senhaCliente;
        this.endereco = endereco;
        this.telefones = telefones;
        this.pedidos = pedidos;
    }

    // 🔹 Getters e Setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<TelefoneCliente> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneCliente> telefones) {
        this.telefones = telefones;
    }

    public List<PedidoCliente> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoCliente> pedidos) {
        this.pedidos = pedidos;
    }
}
