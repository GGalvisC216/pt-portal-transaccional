package com.neoris.portaltransaccional.controller;

import com.neoris.portaltransaccional.model.Cuenta;
import com.neoris.portaltransaccional.service.CuentaService;
import com.neoris.portaltransaccional.exception.AccountNotFoundException;
import com.neoris.portaltransaccional.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping()
    public ResponseEntity<List<Cuenta>> obtenerTodasLasCuentas() {
        return new ResponseEntity<>(cuentaService.obtenerTodasLasCuentas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId (@PathVariable("id") Integer id) {
        return cuentaService.obtenerCuentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorCliente (@PathVariable("id") Integer idCliente) {
        return new ResponseEntity<>(cuentaService.obtenerTodasLasCuentasPorCliente(idCliente), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity guardarCuenta(@Valid @RequestBody Cuenta cuenta) throws ClientNotFoundException {
        return new ResponseEntity(cuentaService.guardarCuenta(cuenta), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity actualizarCuenta(@Valid @RequestBody Cuenta cuenta) throws AccountNotFoundException {
        Cuenta resultado = cuentaService.actualizarCuenta(cuenta);
        if (resultado != null) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCuenta (@PathVariable("id") Integer id) {
        if (cuentaService.borrarCuentaPorId(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
