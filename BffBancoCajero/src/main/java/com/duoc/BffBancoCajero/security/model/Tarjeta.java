package com.duoc.BffBancoCajero.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@Table(name = "tarjetas")
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class Tarjeta {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "numero")
    private String numeroTarjeta;
    @Column(name = "pin")
    private String pin;
    @Column(name = "rol")
    private String rol;
}
