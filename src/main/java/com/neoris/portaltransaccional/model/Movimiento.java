package com.neoris.portaltransaccional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "El movimiento debe tener una fecha asociada")
    private LocalDate fecha;

    @Column(name = "tipo_movimiento", length = 50)
    @NotNull(message = "Debe seleccionar el tipo de movimiento")
    @Size(max = 50, message = "El tipo de movimiento excede los 50 caracteres permitidos")
    @Size(min = 1, message = "El tipo de movimiento no puede ser vacio")
    private String tipoMovimiento;

    @NotNull(message = "El movimiento debe tener un valor asociado")
    private BigDecimal valor;

    private BigDecimal saldo;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @ManyToOne
    @JsonIgnoreProperties("movimientos")
    @JoinColumn(name = "id_cuenta", insertable = false, updatable = false)
    private Cuenta cuenta;

}
