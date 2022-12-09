package com.neoris.portaltransaccional.service.impl;

import com.neoris.portaltransaccional.model.Cuenta;
import com.neoris.portaltransaccional.repository.CuentaRepository;
import com.neoris.portaltransaccional.service.CuentaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public List<Cuenta> obtenerTodasLasCuentas() {
        return (List<Cuenta>) cuentaRepository.findAll();
    }

    @Override
    public List<Cuenta> obtenerTodasLasCuentasPorCliente(Integer idCliente) {
        return cuentaRepository.findByIdCliente(idCliente);
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorId(Integer idCuenta) {
        return cuentaRepository.findById(idCuenta);
    }

    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        return obtenerCuentaPorId(cuenta.getIdCuenta())
                .map(cuentaDB -> {
                    return cuentaRepository.save(cuenta);
                }).orElse(null);
    }

    @Override
    public boolean borrarCuentaPorId(Integer idCuenta) {
        return obtenerCuentaPorId(idCuenta)
                .map(cuentaDB -> {
                    cuentaRepository.deleteById(idCuenta);
                    return true;
                }).orElse(false);
    }
}
