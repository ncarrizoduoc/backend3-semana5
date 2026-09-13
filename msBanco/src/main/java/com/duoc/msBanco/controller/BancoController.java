package com.duoc.msBanco.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.msBanco.model.EstadoCuenta;
import com.duoc.msBanco.model.MovimientoCuenta;
import com.duoc.msBanco.model.TransferenciaRequest;
import com.duoc.msBanco.model.TransferenciaResponse;
import com.duoc.msBanco.service.BancoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController 
@RequestMapping("/api/banco")
public class BancoController {

    private BancoService bancoService;

    @GetMapping("/estado-cuenta/{id}")
    public EstadoCuenta findById(@PathVariable Long id) {
        return bancoService.findById(id);
    }

    @PostMapping("/transferencia")
    public TransferenciaResponse realizarTransferencia(@Valid @RequestBody TransferenciaRequest request){
        return bancoService.realizarTransferencia(request);
    }

    @PostMapping("/movimiento")
    public MovimientoCuenta realizarMovimiento(@Valid @RequestBody MovimientoCuenta movimiento) {
        movimiento.setId(null); // ID null para que se genere automáticamente
        return bancoService.realizarMovimiento(movimiento);
    }

    @GetMapping("/movimiento")
    public List<MovimientoCuenta> findMovimientoByTipo(@RequestParam String tipoMovimiento) {
        return bancoService.findMovimientoByTipo(tipoMovimiento);
    }

    @GetMapping("/movimiento")
    public List<MovimientoCuenta> findMovimientoByFecha(@RequestParam LocalDate fecha) {
        return bancoService.findMovimientoByFecha(fecha);
    }

}
