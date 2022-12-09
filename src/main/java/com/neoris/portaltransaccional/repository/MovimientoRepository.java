package com.neoris.portaltransaccional.repository;

import com.neoris.portaltransaccional.model.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {

    List<Movimiento> findByIdCuenta (Integer idCuenta);

    Optional<Movimiento> findFirstByIdCuentaOrderByIdMovimientoDesc(Integer idCuenta);

}
