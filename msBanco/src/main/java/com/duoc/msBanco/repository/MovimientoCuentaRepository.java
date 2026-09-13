package com.duoc.msBanco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.msBanco.model.MovimientoCuenta;

public interface MovimientoCuentaRepository extends JpaRepository<MovimientoCuenta, Long>{
    List<MovimientoCuenta> findByCuentaId(Long cuentaId);
    List<MovimientoCuenta> findByTransaccion(String transaccion);
    List<MovimientoCuenta> findByFecha(LocalDate fecha);
}
