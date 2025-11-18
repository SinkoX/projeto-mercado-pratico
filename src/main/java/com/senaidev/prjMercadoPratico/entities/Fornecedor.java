package com.senaidev.prjMercadoPratico.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Fornecedor")
    private Long idFornecedor;

    @Column(name = "nome_Fornecedor", nullable = false, length = 100)
    private String nomeFornecedor;

    @Column(name = "email_Fornecedor", nullable = false, length = 50, unique = true)
    private String emailFornecedor;

    @Column(name = "cpf_Fornecedor", nullable = false, unique = true, length = 11)
    private String cpfFornecedor;

    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;
    
    @Column(name = "telefone_fornecedor", nullable = false, length = 15)
    private String telefoneFornecedor;
    
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "fornecedor-produto")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<PedidoFornecedor> pedidos;

    //Construtor 
    public Fornecedor() {
    }

   
    public Fornecedor(Long idFornecedor, String nomeFornecedor, String emailFornecedor, String cpfFornecedor,
                      String cnpj, List<PedidoFornecedor> pedidos, List<Produto> produtos, String telefoneFornecedor) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.emailFornecedor = emailFornecedor;
        this.cpfFornecedor = cpfFornecedor;
        this.cnpj = cnpj;
        this.pedidos = pedidos;
        this.produtos = produtos;
        this.telefoneFornecedor = telefoneFornecedor;
    }

    //Getters e Setters

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

    public List<PedidoFornecedor> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoFornecedor> pedidos) {
        this.pedidos = pedidos;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

	public String getTelefoneFornecedor() {
		return telefoneFornecedor;
	}

	public void setTelefoneFornecedor(String telefoneFornecedor) {
		this.telefoneFornecedor = telefoneFornecedor;
	}
}
