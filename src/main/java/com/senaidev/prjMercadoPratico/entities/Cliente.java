package com.senaidev.prjMercadoPratico.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
@PrimaryKeyJoinColumn(name = "id_usuario") // chave primaria e estrangeira para herança
public class Cliente extends Usuario {

    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "cpf_cliente", nullable = false, unique = true, length = 11)
    private String cpfCliente;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "idEndereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TelefoneCliente> telefones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoCliente> pedidos;
    
    //Construtores
    
    public Cliente() {
       
    }

    public Cliente(String emailUsuario, String senhaUsuario, String nomeCliente, String cpfCliente, LocalDate dataNascimento) {
        super(emailUsuario, senhaUsuario);  // chama o construtor da classe base Usuario
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.dataNascimento = dataNascimento;
    }


    // Getters e setters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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
