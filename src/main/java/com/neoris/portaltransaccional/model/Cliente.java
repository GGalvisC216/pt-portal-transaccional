package com.neoris.portaltransaccional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_cliente")
@Table(name = "clientes")
public class Cliente extends Persona{

    private String password;

    private Boolean estado;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Cuenta> cuentas;

}
