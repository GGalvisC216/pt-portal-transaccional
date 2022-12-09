package com.neoris.portaltransaccional.service.impl;

import com.neoris.portaltransaccional.exception.AccountNotFoundException;
import com.neoris.portaltransaccional.exception.BalanceLowerThanZeroException;
import com.neoris.portaltransaccional.exception.TransactionConflictException;
import com.neoris.portaltransaccional.exception.TransactionNotFoundException;
import com.neoris.portaltransaccional.model.Movimiento;
import com.neoris.portaltransaccional.repository.CuentaRepository;
import com.neoris.portaltransaccional.repository.MovimientoRepository;
import com.neoris.portaltransaccional.service.MovimientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return (List<Movimiento>) movimientoRepository.findAll();
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientosPorCuenta(Integer idCuenta) {
        return movimientoRepository.findByIdCuenta(idCuenta);
    }

    @Override
    public Optional<Movimiento> obtenerMovimientoPorId(Integer idMovimiento) {
        return movimientoRepository.findById(idMovimiento);
    }

    @Override
    public Movimiento guardarMovimiento(Movimiento movimiento) throws Exception{
        BigDecimal valorInicial = obtenerSaldoCuenta(movimiento.getIdCuenta());
        if (valorInicial == null) {
            throw new AccountNotFoundException();
        }

        BigDecimal valor = operarTransaccion(valorInicial, movimiento.getValor(), movimiento.getTipoMovimiento());
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceLowerThanZeroException();
        }
        movimiento.setSaldo(valor);
        return movimientoRepository.save(movimiento);
    }

    @Override
    @Transactional
    public Movimiento actualizarMovimiento(Movimiento movimiento) throws Exception{
        cuentaRepository.findById(movimiento.getIdCuenta()).orElseThrow(AccountNotFoundException::new);
        movimientoRepository.findById(movimiento.getIdMovimiento()).orElseThrow(TransactionNotFoundException::new);

        Movimiento resultado = movimientoRepository.findFirstByIdCuentaOrderByIdMovimientoDesc(movimiento.getIdCuenta())
                .map(movimientoDB -> {
                    if (movimientoDB.getIdMovimiento().equals(movimiento.getIdMovimiento())) {
                        borrarMovimiento(movimiento.getIdMovimiento());
                        try {
                            return guardarMovimiento(movimiento);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        return null;
                    }
                }).orElse(null);
        if (resultado != null) return resultado;
        else throw new TransactionConflictException();
    }

    @Override
    public boolean borrarMovimiento(Integer idMovimiento) {
        return obtenerMovimientoPorId(idMovimiento)
                .map(movimiento -> {
                    Movimiento ultimoMovimiento = movimientoRepository.findFirstByIdCuentaOrderByIdMovimientoDesc(movimiento.getIdCuenta()).orElse(null);
                    if (movimiento.getIdMovimiento().equals(ultimoMovimiento.getIdMovimiento())) {
                        movimientoRepository.deleteById(movimiento.getIdMovimiento());
                        return true;
                    } else {
                        return false;
                    }
                }).orElse(false);
    }

    public BigDecimal obtenerSaldoCuenta (Integer idCuenta) {
        return movimientoRepository.findFirstByIdCuentaOrderByIdMovimientoDesc(idCuenta)
                .map(movimientoDB -> movimientoDB.getSaldo())
                .orElseGet(() -> {
                    return cuentaRepository.findById(idCuenta)
                            .map(cuenta -> cuenta.getSaldoInicial())
                            .orElse(null);
                });
    }

    public BigDecimal operarTransaccion (BigDecimal valorInicial, BigDecimal valorTransaccion, String tipoMovimiento) {
        if ("Retiro".equals(tipoMovimiento)) {
            return valorInicial.subtract(valorTransaccion);
        } else {
            return valorInicial.add(valorTransaccion);
        }
    }
}
