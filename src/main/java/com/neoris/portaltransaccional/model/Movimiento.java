package com.neoris.portaltransaccional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @ManyToOne
    @JsonIgnoreProperties("movimientos")
    @JoinColumn(name = "id_cuenta", insertable = false, updatable = false)
    private Cuenta cuenta;

}
