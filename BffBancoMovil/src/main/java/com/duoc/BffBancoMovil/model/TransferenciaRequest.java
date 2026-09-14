package com.duoc.BffBancoMovil.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data 
public class TransferenciaRequest {

    @NotNull(message = "El id de la cuenta origen no puede ser null")
    @PositiveOrZero(message = "El id de la cuenta origen debe ser un valor positivo o cero")
    private Long cuentaOrigenId;

    @NotNull(message = "El id de la cuenta de destino no puede ser null")
    @PositiveOrZero(message = "El id de la cuenta destino debe ser un valor positivo o cero")
    private Long cuentaDestinoId;
    
    @NotNull(message = "El monto no puede ser null")
    @Positive(message = "El monto debe ser un valor positivo")
    private Integer monto;
    
    @NotNull(message = "La fecha no puede ser null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

}
