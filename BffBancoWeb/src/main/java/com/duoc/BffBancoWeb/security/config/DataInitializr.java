package com.duoc.BffBancoWeb.security.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.duoc.BffBancoWeb.security.model.Usuario;
import com.duoc.BffBancoWeb.security.repository.UsuarioRepository;

@Component
public class DataInitializr implements ApplicationRunner{
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception{

        // Crear un usuario y guardarlo en base de datos para probar autenticación y autorización con JWT
        Usuario usuario = Usuario.builder()
                .username("username")
                .password(passwordEncoder.encode("password"))
                .rol("CLIENTE")
                .build();

        usuarioRepo.save(usuario);
    }
}
