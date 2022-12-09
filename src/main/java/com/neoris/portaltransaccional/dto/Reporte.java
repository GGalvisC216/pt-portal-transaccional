package com.neoris.portaltransaccional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reporte {

    private LocalDate fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private boolean estado;
    private String tipoMovimiento;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;

}
