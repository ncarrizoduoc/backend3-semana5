package com.duoc.BffBancoCajero.security.service;

import com.duoc.BffBancoCajero.security.model.Tarjeta;
import com.duoc.BffBancoCajero.security.repository.TarjetaRepository;
import com.duoc.BffBancoCajero.security.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public UserDetails loadUserByUsername(String numero) throws UsernameNotFoundException {
        Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(numero)
                .orElseThrow(() -> new UsernameNotFoundException("Tarjeta no encontrada: " + numero));
        return new CustomUserDetails(tarjeta);
    }
}
