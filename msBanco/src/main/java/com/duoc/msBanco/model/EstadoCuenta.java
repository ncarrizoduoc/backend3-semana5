package com.duoc.msBanco.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado_cuenta")
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class EstadoCuenta {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaId;

    @Column(name = "ingresos", nullable = false)
    private Integer ingresos;

    @Column(name = "gastos", nullable = false)
    private Integer gastos;

    @Column(name = "saldo", nullable = false)
    private Integer saldo;

    @Transient 
    private List<MovimientoCuenta> movimientos;
}
