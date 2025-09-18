package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionario")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Funcionario extends Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Funcionario")
    private Long idFuncionario;
	
    @Column(name = "nome_Funcionario", nullable = false, length = 100)
    private String nomeFuncionario;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    // Construtores
    public Funcionario() {
        // construtor padrão necessário para JPA
    }

    public Funcionario(String emailUsuario, String senhaUsuario, String cpfUsuario, TipoUsuario tipoUsuario, String nomeFuncionario, String cargo) {
        super(emailUsuario, senhaUsuario, cpfUsuario, tipoUsuario);
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
    }

    // Getters e setters
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
