package com.senaidev.prjMercadoPratico.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Long idFornecedor;

    @Column(name = "nome_fornecedor", nullable = false, length = 100)
    private String nomeFornecedor;

    @Column(name = "email_fornecedor", nullable = false, unique = true, length = 50)
    private String emailFornecedor;

    @Column(name = "cpf_fornecedor", length = 11)
    private String cpfFornecedor;

    @Column(name = "cnpj", length = 14)
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<TelefoneFornecedor> telefones;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<PedidoFornecedor> pedidos;

    // 🔹 Construtor padrão
    public Fornecedor() {
    }

    // 🔹 Construtor com todos os campos
    public Fornecedor(Long idFornecedor, String nomeFornecedor, String emailFornecedor, String cpfFornecedor,
                      String cnpj, List<TelefoneFornecedor> telefones, List<PedidoFornecedor> pedidos) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.emailFornecedor = emailFornecedor;
        this.cpfFornecedor = cpfFornecedor;
        this.cnpj = cnpj;
        this.telefones = telefones;
        this.pedidos = pedidos;
    }

    // 🔹 Getters e Setters

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Long idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    public String getCpfFornecedor() {
        return cpfFornecedor;
    }

    public void setCpfFornecedor(String cpfFornecedor) {
        this.cpfFornecedor = cpfFornecedor;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<TelefoneFornecedor> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneFornecedor> telefones) {
        this.telefones = telefones;
    }

    public List<PedidoFornecedor> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoFornecedor> pedidos) {
        this.pedidos = pedidos;
    }
}
