package com.neoris.portaltransaccional.service.impl;

import com.neoris.portaltransaccional.exception.ClientAlreadyExistsException;
import com.neoris.portaltransaccional.model.Cliente;
import com.neoris.portaltransaccional.model.Persona;
import com.neoris.portaltransaccional.repository.ClienteRepository;
import com.neoris.portaltransaccional.service.ClienteService;
import com.neoris.portaltransaccional.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Persona> obtenerTodosLosClientes() {
        return ((List<Persona>) clienteRepository.findAll());
    }

    @Override
    public Optional<Persona> obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) throws ClientAlreadyExistsException {
        Cliente clienteDB = clienteRepository.findByIdentificacion(cliente.getIdentificacion()).orElse(null);
        if (clienteDB == null) {
            return clienteRepository.save(cliente);
        } else {
            throw new ClientAlreadyExistsException();
        }
    }

    @Override
    public boolean borrarClientePorId(Integer id) {
        return obtenerClientePorId(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return true;
                }).orElse(false);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws ClientNotFoundException {
        return obtenerClientePorId(cliente.getIdPersona()).map(
                clienteBD -> {
                    return clienteRepository.save(cliente);
                }).orElseThrow(ClientNotFoundException::new);
    }
}
