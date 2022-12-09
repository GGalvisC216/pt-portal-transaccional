package com.neoris.portaltransaccional.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personas")
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(length = 150)
    @Size(max = 150, message = "La longitud del nombre debe ser maximo de 150 caracteres")
    private String nombre;

    @Column(length = 1)
    @Size(max = 1, message = "El genero se debe enviar en un caracter Masculino (M), Femenino (F)")
    private String genero;

    private Integer edad;

    @Column(length = 50, unique = true)
    @Size(max = 50, message = "La identificacion debe ser maximo de 50 caracteres")
    @Size(min = 1, message = "La identificacion no puede ser vacia")
    @NotNull(message = "La identificacion no puede ser nula")
    private String identificacion;

    @Column(length = 80)
    @Size(max = 80, message = "La direccion debe ser de maximo 80 caracteres")
    private String direccion;


    private Long telefono;

}
