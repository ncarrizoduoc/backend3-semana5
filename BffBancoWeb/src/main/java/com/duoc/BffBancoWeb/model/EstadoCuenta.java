package com.duoc.BffBancoWeb.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class EstadoCuenta {
    private Long cuentaId;
    private Integer ingresos;
    private Integer gastos;
    private Integer saldo;
    private List<MovimientoCuenta> movimientos;
}
