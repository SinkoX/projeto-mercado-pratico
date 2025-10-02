package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Funcionario")
	private Long id;

	
    @Column(name = "nome_Funcionario", nullable = false, length = 100)
    private String nomeFuncionario;
    
    @Column(name = "email_Funcionario", nullable = false, length = 100, unique = true)
    private String emailFuncionario;
    
    @Column(name = "senha_Funcionario", nullable = false, length = 100)
    private String senhaFuncionario;
    
    @Column(name = "cpf_Funcionario", nullable = false, length = 11, unique = true)
    private String cpfFuncionario;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;
    
    @ManyToOne
    @JoinColumn(name = "id_Tipo_Usuario")
    private TipoUsuario tipoUsuario;

    // Construtores
    public Funcionario() {
        // construtor padrão necessário para JPA
    }

    public Funcionario(Long id, String nomeFuncionario, String emailFuncionario, String senhaFuncionario, 
    		String cpfFuncionario, String cargo, TipoUsuario tipoUsuario) {
        this.id = id;
    	this.nomeFuncionario = nomeFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.senhaFuncionario = senhaFuncionario;
        this.cpfFuncionario = cpfFuncionario;
        this.cargo = cargo;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getSenhaFuncionario() {
		return senhaFuncionario;
	}

	public void setSenhaFuncionario(String senhaFuncionario) {
		this.senhaFuncionario = senhaFuncionario;
	}

	public String getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
