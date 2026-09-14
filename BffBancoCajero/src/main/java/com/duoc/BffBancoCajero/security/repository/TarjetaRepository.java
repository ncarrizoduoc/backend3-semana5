package com.duoc.BffBancoCajero.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.BffBancoCajero.security.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Optional<Tarjeta> findByNumeroTarjeta(String numero);

}
