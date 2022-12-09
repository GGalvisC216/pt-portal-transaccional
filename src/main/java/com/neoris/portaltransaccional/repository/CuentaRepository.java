package com.neoris.portaltransaccional.repository;

import com.neoris.portaltransaccional.model.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {

    List<Cuenta> findByIdCliente(Integer idCliente);

}
