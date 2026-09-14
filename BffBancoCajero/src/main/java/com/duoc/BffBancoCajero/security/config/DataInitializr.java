package com.duoc.BffBancoCajero.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.duoc.BffBancoCajero.security.model.Tarjeta;
import com.duoc.BffBancoCajero.security.repository.TarjetaRepository;

@Component
public class DataInitializr implements ApplicationRunner{
    @Autowired
    private TarjetaRepository tarjetaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception{

        // Crear una tarjeta y guardarla en base de datos para probar autenticación y autorización con JWT
        Tarjeta tarjeta = Tarjeta.builder()
                .numeroTarjeta("1234123412341234")
                .pin(passwordEncoder.encode("1234"))
                .rol("CLIENTE")
                .build();

        tarjetaRepo.save(tarjeta);
    }
}
