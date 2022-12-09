package com.neoris.portaltransaccional.utils;

import com.neoris.portaltransaccional.dto.ClienteTest;
import com.neoris.portaltransaccional.dto.CuentaTest;
import com.neoris.portaltransaccional.dto.MovimientoTest;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestUtils {

    public static ClienteTest generarCliente() {
        ClienteTest cliente = new ClienteTest();
        cliente.setEstado(true);
        cliente.setPassword("A54asd6w8");
        cliente.setDireccion("Calle 354 - 35");
        cliente.setEdad(31);
        cliente.setGenero("M");
        cliente.setIdentificacion("3214567801");
        cliente.setNombre("Roberto Ramirez");
        cliente.setTelefono(3214578L);
        return cliente;
    }

    public static CuentaTest generarCuenta() {
        CuentaTest cuenta = new CuentaTest();
        cuenta.setNumeroCuenta("215487");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setEstado(true);
        cuenta.setSaldoInicial(BigDecimal.valueOf(1500));
        return cuenta;
    }

    public static MovimientoTest generarMovimiento() {
        MovimientoTest movimiento = new MovimientoTest();
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(BigDecimal.valueOf(7500));
        movimiento.setFecha(LocalDate.of(2022,12,9));
        return movimiento;
    }

}
