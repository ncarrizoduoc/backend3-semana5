package com.duoc.msBanco.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.msBanco.model.EstadoCuenta;
import com.duoc.msBanco.model.MovimientoCuenta;
import com.duoc.msBanco.model.TransferenciaRequest;
import com.duoc.msBanco.service.BancoService;

import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController 
@RequestMapping("/api/banco")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping("/estado-cuenta/{id}")
    public EstadoCuenta findById(@PathVariable Long id) {
        return bancoService.findById(id);
    }

    @PostMapping("/transferencias")
    public MovimientoCuenta realizarTransferencia(@Valid @RequestBody TransferenciaRequest request){
        return bancoService.realizarTransferencia(request);
    }

    @PostMapping("/movimientos")
    public MovimientoCuenta realizarMovimiento(@Valid @RequestBody MovimientoCuenta movimiento) {
        movimiento.setId(null); // ID null para que se genere automáticamente
        return bancoService.realizarMovimiento(movimiento);
    }

    @GetMapping("/movimientos")
    public List<MovimientoCuenta> verMovimientos(
        @RequestParam(required = false) String tipoMovimiento,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha
    ) {
        if (tipoMovimiento != null && fecha != null) {
            return bancoService.findMovimientoByTipoAndFecha(tipoMovimiento, fecha);
        } else if (tipoMovimiento != null) {
            return bancoService.findMovimientoByTipo(tipoMovimiento);
        } else if (fecha != null) {
            return bancoService.findMovimientoByFecha(fecha);
        } else {
            return bancoService.findAllMovimientos();
        }
    }

}
