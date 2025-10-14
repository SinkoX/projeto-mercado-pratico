package com.senaidev.prjMercadoPratico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailUsuario(String emailUsuario);

    Usuario findByCpfUsuario(String cpfUsuario);
    
    Usuario findByEmailUsuarioAndSenhaUsuario(String emailUsuario, String senhaUsuario);


}
