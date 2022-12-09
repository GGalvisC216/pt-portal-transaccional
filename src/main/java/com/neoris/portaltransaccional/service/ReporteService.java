package com.neoris.portaltransaccional.service;

import com.neoris.portaltransaccional.dto.Reporte;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {

    List<Reporte> generarReporte(LocalDate fechaInicial, LocalDate fechaFinal, Integer idCliente);

}
