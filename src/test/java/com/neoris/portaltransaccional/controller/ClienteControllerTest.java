package com.neoris.portaltransaccional.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.portaltransaccional.dto.ClienteTest;
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

import static com.neoris.portaltransaccional.utils.TestUtils.generarCliente;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void clienteSeAlmacenaActualizaYEliminaCorrectamente() throws Exception{

        MvcResult resultadoClienteCreado = mvc.perform(
                        MockMvcRequestBuilders.post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(generarCliente())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPersona").exists())
                .andReturn();

        ClienteTest resultadoCliente = objectMapper.readValue(resultadoClienteCreado.getResponse().getContentAsString(), ClienteTest.class);

        mvc.perform(MockMvcRequestBuilders
                        .get("/clientes/" + resultadoCliente.getIdPersona())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPersona").exists())
                .andExpect(jsonPath("$.nombre", is("Roberto Ramirez")))
                .andExpect(jsonPath("$.edad", is(31)))
                .andExpect(jsonPath("$.identificacion", is("3214567801")))
                .andExpect(jsonPath("$.direccion", is("Calle 354 - 35")));

        resultadoCliente.setNombre("Jose Roberto Ramirez");
        resultadoCliente.setEdad(32);

        mvc.perform(MockMvcRequestBuilders.put("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resultadoCliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPersona").exists())
                .andExpect(jsonPath("$.nombre", is("Jose Roberto Ramirez")))
                .andExpect(jsonPath("$.edad", is(32)))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders
                        .delete("/clientes/" + resultadoCliente.getIdPersona())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
