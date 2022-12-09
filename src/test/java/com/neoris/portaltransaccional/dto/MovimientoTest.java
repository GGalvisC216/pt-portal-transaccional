package com.neoris.portaltransaccional.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovimientoTest {

    private Integer idMovimiento;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    private Integer idCuenta;

}
