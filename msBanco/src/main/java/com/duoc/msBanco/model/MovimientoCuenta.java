package com.duoc.msBanco.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "movimiento_cuenta")
@Data
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class MovimientoCuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "transaccion", nullable = false)
    private String transaccion;

    @Column(name = "cuenta_id", nullable = false)
    private Long cuentaId;

    @Column(name = "monto", nullable = false)
    private Integer monto;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

}
