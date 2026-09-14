package com.duoc.BffBancoMovil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.BffBancoMovil.model.EstadoCuenta;
import com.duoc.BffBancoMovil.model.EstadoCuentaResponse;
import com.duoc.BffBancoMovil.model.MovimientoCuenta;
import com.duoc.BffBancoMovil.model.MovimientoCuentaResponse;
import com.duoc.BffBancoMovil.service.BancoMovilService;
import com.duoc.BffBancoMovil.model.TransferenciaRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/banco/movil")
public class BancoMovilController {

    private final BancoMovilService bancoMovilService;

    public BancoMovilController(BancoMovilService bancoMovilService) {
        this.bancoMovilService = bancoMovilService;
    }

    @GetMapping("/estado-cuenta/{id}")
    public ResponseEntity<EstadoCuentaResponse> getEstadoCuenta(@PathVariable Long id) {
        EstadoCuenta estadoCuenta = bancoMovilService.getEstadoCuenta(id);
        if (estadoCuenta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new EstadoCuentaResponse(estadoCuenta));
        
    }

    @PostMapping("/transferencias")
    public ResponseEntity<MovimientoCuentaResponse> realizarTransferencia(@Valid @RequestBody TransferenciaRequest request) {
        MovimientoCuenta movimiento = bancoMovilService.realizarTransferencia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MovimientoCuentaResponse(movimiento));
    }

}
