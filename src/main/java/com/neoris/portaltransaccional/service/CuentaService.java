package com.neoris.portaltransaccional.service;

import com.neoris.portaltransaccional.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    List<Cuenta> obtenerTodasLasCuentas();

    List<Cuenta> obtenerTodasLasCuentasPorCliente(Integer idCliente);

    Optional<Cuenta> obtenerCuentaPorId(Integer idCuenta);

    Cuenta guardarCuenta(Cuenta cuenta);

    Cuenta actualizarCuenta(Cuenta cuenta);

    boolean borrarCuentaPorId(Integer idCuenta);

}
