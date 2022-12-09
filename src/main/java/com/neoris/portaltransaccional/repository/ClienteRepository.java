package com.neoris.portaltransaccional.repository;

import com.neoris.portaltransaccional.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends PersonaRepository{

    Optional<Cliente> findByIdentificacion (String identificacion);
}
