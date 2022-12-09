package com.neoris.portaltransaccional.controller;

import com.neoris.portaltransaccional.model.Movimiento;
import com.neoris.portaltransaccional.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping()
    public ResponseEntity<List<Movimiento>> obtenerTodosLosMovimientos () {
        return new ResponseEntity<>(movimientoService.obtenerTodosLosMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerMovimientoPorId(@PathVariable("id") Integer idMovimiento) {
        return movimientoService.obtenerMovimientoPorId(idMovimiento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable("id") Integer idCuenta) {
        return new ResponseEntity<>(movimientoService.obtenerTodosLosMovimientosPorCuenta(idCuenta), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity guardarMovimiento(@Valid @RequestBody Movimiento movimiento) {
        Movimiento resultado = movimientoService.guardarMovimiento(movimiento);
        if (resultado != null) {
            return new ResponseEntity(resultado, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Saldo no disponible", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity actualizarMovimiento(@Valid @RequestBody Movimiento movimiento) {
        Movimiento resultado = movimientoService.actualizarMovimiento(movimiento);
        if (resultado != null) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarMovimiento(@PathVariable("id") Integer idMovimiento) {
        if (movimientoService.borrarMovimiento(idMovimiento)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
