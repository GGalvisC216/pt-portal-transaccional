package com.neoris.portaltransaccional.service.impl;

import com.neoris.portaltransaccional.dto.Reporte;
import com.neoris.portaltransaccional.repository.MovimientoRepository;
import com.neoris.portaltransaccional.service.ReporteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final MovimientoRepository movimientoRepository;

    public ReporteServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }


    @Override
    public List<Reporte> generarReporte(LocalDate fechaInicial, LocalDate fechaFinal, Integer idCliente) {
        return movimientoRepository.generateReport(fechaInicial, fechaFinal, idCliente);
    }
}
