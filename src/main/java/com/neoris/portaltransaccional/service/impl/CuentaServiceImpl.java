package com.neoris.portaltransaccional.service.impl;

import com.neoris.portaltransaccional.model.Cuenta;
import com.neoris.portaltransaccional.repository.CuentaRepository;
import com.neoris.portaltransaccional.service.ClienteService;
import com.neoris.portaltransaccional.service.CuentaService;
import com.neoris.portaltransaccional.exception.AccountNotFoundException;
import com.neoris.portaltransaccional.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteService clienteService;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteService clienteService) {
        this.cuentaRepository = cuentaRepository;
        this.clienteService = clienteService;
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
    public Cuenta guardarCuenta(Cuenta cuenta) throws ClientNotFoundException {
        return clienteService.obtenerClientePorId(cuenta.getIdCliente())
                .map(cliente -> cuentaRepository.save(cuenta)
                ).orElseThrow(ClientNotFoundException::new);

    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) throws AccountNotFoundException{
        return obtenerCuentaPorId(cuenta.getIdCuenta())
                .map(cuentaDB -> {
                    return cuentaRepository.save(cuenta);
                }).orElseThrow(AccountNotFoundException::new);
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
