package com.neoris.portaltransaccional.service;

import com.neoris.portaltransaccional.model.Cuenta;
import com.neoris.portaltransaccional.exception.AccountNotFoundException;
import com.neoris.portaltransaccional.exception.ClientNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    List<Cuenta> obtenerTodasLasCuentas();

    List<Cuenta> obtenerTodasLasCuentasPorCliente(Integer idCliente);

    Optional<Cuenta> obtenerCuentaPorId(Integer idCuenta);

    Cuenta guardarCuenta(Cuenta cuenta) throws ClientNotFoundException;

    Cuenta actualizarCuenta(Cuenta cuenta) throws AccountNotFoundException;

    boolean borrarCuentaPorId(Integer idCuenta);

}
