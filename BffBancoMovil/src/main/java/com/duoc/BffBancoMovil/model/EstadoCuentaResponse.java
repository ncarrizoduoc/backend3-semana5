package com.duoc.BffBancoMovil.model;

import java.util.List;

import lombok.Data;

@Data 
public class EstadoCuentaResponse {
    private Long cuentaId;
    private Integer ingresos;
    private Integer gastos;
    private Integer saldo;
    private List<MovimientoCuentaResponse> ultimosMovimientos; // Listado con los últimos 5 movimientos de la cuenta

    public EstadoCuentaResponse(EstadoCuenta estadoCuenta){
        this.cuentaId = estadoCuenta.getCuentaId();
        this.ingresos = estadoCuenta.getIngresos();
        this.gastos = estadoCuenta.getGastos();
        this.saldo = estadoCuenta.getSaldo();
        this.ultimosMovimientos = estadoCuenta.getMovimientos().stream()
            .map(MovimientoCuentaResponse::new)
            .toList();
    }

}
