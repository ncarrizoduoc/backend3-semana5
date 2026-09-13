package com.duoc.BffBancoWeb.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class MovimientoCuenta {
    
    private Long id;
    
    @NotNull(message = "El id de la cuenta no puede ser null")
    @PositiveOrZero(message = "El id de la cuenta debe ser un valor positivo o cero")
    private Long cuentaId;

    @NotNull(message = "La fecha no puede ser null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull(message = "La transacción no puede ser null")
    @NotBlank(message = "La transacción no puede ser un texto vacío")
    private String transaccion;

    @NotNull (message = "El monto no puede ser null")
    private Integer monto;

    // La descripción es opcional
    private String descripcion;

}
