package com.neoris.portaltransaccional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "numero_cuenta", length = 50)
    @NotNull(message = "El numero de cuenta no puede ser nulo")
    @Size(min = 1, message = "El numero de cuenta no puede ser vacio")
    @Size(max = 50, message = "El numero de cuenta debe ser maximo de 50 caracteres")
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", length = 50)
    @Size(max = 50, message = "El tipo de cuenta debe ser maximo de 50 caracteres")
    private String tipoCuenta;

    @Column(name = "saldo_inicial")
    @NotNull(message = "El saldo inicial no puede ser vacio")
    private BigDecimal saldoInicial;

    private Boolean estado;

    @Column(name = "id_cliente")
    @NotNull(message = "La cuenta debe estar asociada a un cliente")
    private Integer idCliente;

    @ManyToOne
    @JsonIgnoreProperties("cuentas")
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta")
    @JsonIgnoreProperties("cuenta")
    private List<Movimiento> movimientos;

}
