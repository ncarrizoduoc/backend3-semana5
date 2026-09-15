package com.duoc.BffBancoCajero.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.BffBancoCajero.model.EstadoCuenta;
import com.duoc.BffBancoCajero.model.EstadoCuentaResponse;
import com.duoc.BffBancoCajero.model.MovimientoCuenta;
import com.duoc.BffBancoCajero.model.MovimientoCuentaRequest;
import com.duoc.BffBancoCajero.model.MovimientoCuentaResponse;
import com.duoc.BffBancoCajero.service.BancoCajeroService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/banco/cajero")
public class BancoCajeroController {

    private final BancoCajeroService bancoCajeroService;

    public BancoCajeroController(BancoCajeroService bancoCajeroService) {
        this.bancoCajeroService = bancoCajeroService;
    }

    @GetMapping("/estado-cuenta/{id}")
    public ResponseEntity<EstadoCuentaResponse> getEstadoCuenta(@PathVariable Long id) {
        EstadoCuenta estadoCuenta = bancoCajeroService.getEstadoCuenta(id);
        if (estadoCuenta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new EstadoCuentaResponse(estadoCuenta));
        
    }

    @PostMapping("/movimientos")
    public ResponseEntity<MovimientoCuentaResponse> realizarMovimiento(@Valid @RequestBody MovimientoCuentaRequest request) {
        MovimientoCuentaResponse movimiento = new MovimientoCuentaResponse(bancoCajeroService.realizarMovimiento(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }
    

}
