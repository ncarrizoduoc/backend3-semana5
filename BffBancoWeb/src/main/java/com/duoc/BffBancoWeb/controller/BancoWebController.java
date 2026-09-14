package com.duoc.BffBancoWeb.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.BffBancoWeb.model.EstadoCuenta;
import com.duoc.BffBancoWeb.model.MovimientoCuenta;
import com.duoc.BffBancoWeb.model.TransferenciaRequest;
import com.duoc.BffBancoWeb.service.BancoWebService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/banco/web")
public class BancoWebController {

    private final BancoWebService bancoWebService;

    public BancoWebController(BancoWebService bancoMovilService) {
        this.bancoWebService = bancoMovilService;
    }

    @GetMapping("/estado-cuenta/{id}")
    public ResponseEntity<EstadoCuenta> getEstadoCuenta(@PathVariable Long id) {
        EstadoCuenta estadoCuenta = bancoWebService.getEstadoCuenta(id);
        if (estadoCuenta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((estadoCuenta));
        
    }

    @PostMapping("/transferencias")
    public ResponseEntity<MovimientoCuenta> realizarTransferencia(@Valid @RequestBody TransferenciaRequest request) {
        MovimientoCuenta transferencia = bancoWebService.realizarTransferencia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    }

    @PostMapping("/movimientos")
    public ResponseEntity<MovimientoCuenta> realizarMovimiento(@Valid @RequestBody MovimientoCuenta request) {
        MovimientoCuenta movimiento = bancoWebService.realizarMovimiento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoCuenta>> verMovimientos(
        @RequestParam(required = false) String tipoMovimiento,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha
    ) {
        return ResponseEntity.ok(bancoWebService.verMovimientos(tipoMovimiento, fecha));
    }
    
    

}
