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
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable("id") Integer idMovimiento) {
        return movimientoService.obtenerMovimientoPorId(idMovimiento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable("id") Integer idCuenta) {
        return new ResponseEntity<>(movimientoService.obtenerTodosLosMovimientosPorCuenta(idCuenta), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Movimiento> guardarMovimiento(@Valid @RequestBody Movimiento movimiento) throws Exception {
        return new ResponseEntity<>(movimientoService.guardarMovimiento(movimiento), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Movimiento> actualizarMovimiento(@Valid @RequestBody Movimiento movimiento) throws Exception {
        return new ResponseEntity<>(movimientoService.actualizarMovimiento(movimiento), HttpStatus.OK);

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
