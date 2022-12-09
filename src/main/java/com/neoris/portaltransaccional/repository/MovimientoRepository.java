package com.neoris.portaltransaccional.repository;

import com.neoris.portaltransaccional.dto.Reporte;
import com.neoris.portaltransaccional.model.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {

    List<Movimiento> findByIdCuenta (Integer idCuenta);

    Optional<Movimiento> findFirstByIdCuentaOrderByIdMovimientoDesc(Integer idCuenta);

    @Query("select new com.neoris.portaltransaccional.dto.Reporte(m.fecha, cl.nombre, c.numeroCuenta, c.tipoCuenta, c.saldoInicial, c.estado, m.tipoMovimiento, m.valor, m.saldo) " +
            "from Movimiento m, Cuenta c, Cliente cl " +
            "where m.idCuenta = c.idCuenta and c.idCliente = cl.idPersona " +
            "and m.fecha between :fechaInicial and :fechaFinal and cl.idPersona = :idCliente " +
            "order by c.idCuenta, m.fecha")
    List<Reporte> generateReport(LocalDate fechaInicial, LocalDate fechaFinal, Integer idCliente);

}
