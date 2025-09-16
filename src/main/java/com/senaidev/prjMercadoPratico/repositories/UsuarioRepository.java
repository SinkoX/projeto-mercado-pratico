package com.senaidev.prjMercadoPratico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senaidev.prjMercadoPratico.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailUsuario(String emailUsuario);

    Usuario findByCpfUsuario(String cpfUsuario);

}
