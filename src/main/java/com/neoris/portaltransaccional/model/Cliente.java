package com.neoris.portaltransaccional.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_cliente")
@Table(name = "clientes")
public class Cliente extends Persona{

    private String password;

    private Boolean estado;

}
