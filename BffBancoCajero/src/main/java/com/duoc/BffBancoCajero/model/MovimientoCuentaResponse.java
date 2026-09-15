package com.duoc.BffBancoCajero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MovimientoCuentaResponse {
    private Long cuentaId;
    private Integer monto;
    private String tipoTransaccion;

    public MovimientoCuentaResponse(MovimientoCuenta mov){
        cuentaId = mov.getCuentaId();
        monto = mov.getMonto();
        tipoTransaccion = mov.getTransaccion();
    }

}
