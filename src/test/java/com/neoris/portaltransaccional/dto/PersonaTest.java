package com.neoris.portaltransaccional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class PersonaTest {

    private Integer idPersona;

    private String nombre;

    private String genero;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private Long telefono;

}
