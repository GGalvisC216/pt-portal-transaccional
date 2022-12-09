package com.neoris.portaltransaccional.controller;

import com.neoris.portaltransaccional.model.Cliente;
import com.neoris.portaltransaccional.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity obtenerTodosLosClientes() {
        return new ResponseEntity<>(clienteService.obtenerTodosLosClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerClientePorId (@PathVariable Integer id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.noContent().build());
    }

    @PostMapping()
    public ResponseEntity guardarCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity(clienteService.guardarCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity actualizarCliente(@RequestBody Cliente cliente) {
        Cliente resultado = clienteService.actualizarCliente(cliente);
        if (resultado != null) {
            return new ResponseEntity(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCliente(@PathVariable("id") Integer id) {
        if (clienteService.borrarClientePorId(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
}
