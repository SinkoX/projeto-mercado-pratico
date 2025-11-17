package com.senaidev.prjMercadoPratico.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 100)
    private String nomeUsuario;
    
    @Column(name = "email_usuario", nullable = false, unique = true, length = 50)
    private String emailUsuario;

    @Column(name = "senha_usuario", nullable = false, length = 255)
    private String senhaUsuario;

    @Column(name = "cpf_usuario", nullable = false, unique = true, length = 11)
    private String cpfUsuario;
    
    @Column(name = "telefone_usuario", nullable = false, length = 15)
    private String telefoneUsuario;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private Carrinho carrinho;

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> endereco = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "id_Tipo_Usuario")
    private TipoUsuario tipoUsuario;

    // Construtores
    public Usuario() {
    }

    public Usuario(Long idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario, String cpfUsuario, TipoUsuario tipoUsuario,
    		String telefoneUsuario, List<Endereco> endereco) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
    	this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.cpfUsuario = cpfUsuario;
        this.tipoUsuario = tipoUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.endereco = endereco;
    }

    // Getters e setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
  
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
}
