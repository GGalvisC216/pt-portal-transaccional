package com.neoris.portaltransaccional.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.portaltransaccional.dto.ClienteTest;
import com.neoris.portaltransaccional.dto.CuentaTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.neoris.portaltransaccional.utils.TestUtils.generarCliente;
import static com.neoris.portaltransaccional.utils.TestUtils.generarCuenta;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CuentaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void cuentaSeAlmacenaActualizaYEliminaCorrectamente() throws Exception {

        MvcResult resultadoClienteCreado = mvc.perform(
                        MockMvcRequestBuilders.post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(generarCliente())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPersona").exists())
                .andReturn();

        ClienteTest resultadoCliente = objectMapper.readValue(resultadoClienteCreado.getResponse().getContentAsString(), ClienteTest.class);

        CuentaTest cuenta = generarCuenta();
        cuenta.setIdCliente(resultadoCliente.getIdPersona());

        MvcResult resultadoCuentaCreada = mvc.perform(
                        MockMvcRequestBuilders.post("/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCuenta").exists())
                .andExpect(jsonPath("$.numeroCuenta", is("215487")))
                .andReturn();

        CuentaTest resultadoCuenta = objectMapper.readValue(resultadoCuentaCreada.getResponse().getContentAsString(), CuentaTest.class);

        mvc.perform(MockMvcRequestBuilders
                        .get("/cuentas/" + resultadoCuenta.getIdCuenta())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCuenta").exists())
                .andExpect(jsonPath("$.numeroCuenta", is("215487")))
                .andExpect(jsonPath("$.tipoCuenta", is("Ahorro")))
                .andExpect(jsonPath("$.saldoInicial", is(1500.0)));

        resultadoCuenta.setSaldoInicial(BigDecimal.valueOf(10600.0));

        mvc.perform(MockMvcRequestBuilders.put("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resultadoCuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCuenta").exists())
                .andExpect(jsonPath("$.saldoInicial", is(10600.0)))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders
                        .delete("/cuentas/" + resultadoCuenta.getIdCuenta())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/clientes/" + resultadoCliente.getIdPersona())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
