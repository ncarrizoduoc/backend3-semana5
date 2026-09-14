package com.duoc.BffBancoCajero.security.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "Debe ingresar un numero de tarjeta")
    @Pattern(regexp = "[0-9]{16}", message = "El número de tarjeta debe ser un número de 16 dígitos")
    private String numeroTarjeta;

    @NotNull(message = "Debe ingresar un pin")
    @Pattern(regexp = "[0-9]{4}", message = "El pin de la tarjeta debe ser un número de 4 dígitos")
    private String pin;
}
