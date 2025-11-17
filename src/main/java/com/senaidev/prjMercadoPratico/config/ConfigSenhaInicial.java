package com.senaidev.prjMercadoPratico.config;

import com.senaidev.prjMercadoPratico.entities.Usuario;
import com.senaidev.prjMercadoPratico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ConfigSenhaInicial implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        for (Usuario u : usuarioRepository.findAll()) {

            // Se NÃO estiver criptografada (não começa com $2a)
            if (u.getSenhaUsuario() != null && !u.getSenhaUsuario().startsWith("$2a$")) {
                String senhaCriptografada = encoder.encode(u.getSenhaUsuario());
                u.setSenhaUsuario(senhaCriptografada);
                usuarioRepository.save(u);

                System.out.println("Senha criptografada automaticamente para: " + u.getEmailUsuario());
            }
        }
    }
}
