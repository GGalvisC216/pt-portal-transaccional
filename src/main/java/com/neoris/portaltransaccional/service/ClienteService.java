package com.neoris.portaltransaccional.service;

import com.neoris.portaltransaccional.model.Cliente;
import com.neoris.portaltransaccional.model.Persona;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Persona> obtenerTodosLosClientes();

    Optional<Persona> obtenerClientePorId(Integer id);

    Cliente guardarCliente (Cliente cliente);

    boolean borrarClientePorId (Integer id);

    Cliente actualizarCliente (Cliente cliente);

}
