package com.neoris.portaltransaccional.controller;

import com.neoris.portaltransaccional.dto.Reporte;
import com.neoris.portaltransaccional.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping()
    public ResponseEntity<List<Reporte>> obtenerReporte(
            @RequestParam(name = "fechaInicial") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaInicial,
            @RequestParam(name = "fechaFinal") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaFinal,
            @RequestParam(name = "idCliente") Integer idCliente) {
        return new ResponseEntity<>(reporteService.generarReporte(fechaInicial, fechaFinal, idCliente),HttpStatus.OK);
    }

}
