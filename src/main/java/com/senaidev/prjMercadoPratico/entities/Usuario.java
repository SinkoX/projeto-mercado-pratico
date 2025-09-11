package com.senaidev.prjMercadoPratico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED) 
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "email_usuario", nullable = false, unique = true, length = 50)
    private String emailUsuario;

    @Column(name = "senha_usuario", nullable = false, length = 255)
    private String senhaUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuario tipoUsuario;

    // Construtores
    public Usuario() {
    }

    public Usuario(String emailUsuario, String senhaUsuario, TipoUsuario tipoUsuario) {
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
}
