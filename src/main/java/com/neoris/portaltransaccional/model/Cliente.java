package com.neoris.portaltransaccional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_cliente")
@Table(name = "clientes")
public class Cliente extends Persona{

    @Column(length = 100)
    @Size(max = 100, message = "La contraseña debe ser maximo de 100 caracteres")
    @NotNull(message = "La contraseña no puede ser vacia")
    private String password;

    @NotNull(message = "El estado del cliente no puede ser vacio")
    private Boolean estado;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Cuenta> cuentas;

}
