package com.neoris.portaltransaccional.controller;

import com.neoris.portaltransaccional.exception.ClientAlreadyExistsException;
import com.neoris.portaltransaccional.model.Cliente;
import com.neoris.portaltransaccional.model.Persona;
import com.neoris.portaltransaccional.service.ClienteService;
import com.neoris.portaltransaccional.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<Persona>> obtenerTodosLosClientes() {
        return new ResponseEntity<>(clienteService.obtenerTodosLosClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerClientePorId (@PathVariable Integer id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.noContent().build());
    }

    @PostMapping()
    public ResponseEntity<Cliente> guardarCliente(@Valid @RequestBody Cliente cliente) throws ClientAlreadyExistsException {
        return new ResponseEntity<>(clienteService.guardarCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Cliente> actualizarCliente(@Valid @RequestBody Cliente cliente) throws ClientNotFoundException {
        return new ResponseEntity<>(clienteService.actualizarCliente(cliente), HttpStatus.OK);
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
