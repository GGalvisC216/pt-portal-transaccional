package com.neoris.portaltransaccional.service;

import com.neoris.portaltransaccional.model.Movimiento;

import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    List<Movimiento> obtenerTodosLosMovimientos();

    List<Movimiento> obtenerTodosLosMovimientosPorCuenta(Integer idCuenta);

    Optional<Movimiento> obtenerMovimientoPorId(Integer idMovimiento);

    Movimiento guardarMovimiento(Movimiento movimiento);

    Movimiento actualizarMovimiento(Movimiento movimiento);

    boolean borrarMovimiento(Integer idMovimiento);

}
