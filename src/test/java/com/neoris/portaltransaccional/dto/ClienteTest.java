package com.neoris.portaltransaccional.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class ClienteTest extends PersonaTest {

    private String password;

    private Boolean estado;

}
