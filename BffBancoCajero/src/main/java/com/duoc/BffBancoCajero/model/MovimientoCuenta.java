package com.duoc.BffBancoCajero.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class MovimientoCuenta {
    
    private Long id;
    private Long cuentaId;
    private LocalDate fecha;
    private String transaccion;
    private Integer monto;
    private String descripcion;

}
