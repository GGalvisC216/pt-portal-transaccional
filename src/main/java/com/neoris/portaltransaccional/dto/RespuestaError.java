package com.neoris.portaltransaccional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class RespuestaError {

    private String excepcion;
    private String mensaje;

}
