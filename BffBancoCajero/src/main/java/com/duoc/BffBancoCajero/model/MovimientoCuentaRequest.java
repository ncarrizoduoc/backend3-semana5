package com.duoc.BffBancoCajero.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MovimientoCuentaRequest {

    @NotNull(message = "El ID de la cuenta no puede ser null")
    @PositiveOrZero(message = "El ID de la cuenta debe ser un número positivo o cero")
    private Long cuentaId;
    
    @NotNull(message = "La fecha no puede ser null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    
    @NotNull(message = "El tipo de transacción no puede ser null")
    @Pattern(regexp = "^(deposito|retiro)$", message = "El tipo de transacción debe ser 'deposito' o 'retiro'")
    private String transaccion;
    
    @NotNull(message = "El monto no puede ser null")
    @Positive(message = "El monto debe ser un número positivo")
    private Integer monto;

}
