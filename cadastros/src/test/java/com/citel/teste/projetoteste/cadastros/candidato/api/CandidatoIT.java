package com.citel.teste.projetoteste.cadastros.candidato.api;

import com.citel.teste.projetoteste.cadastros.ApplicationTests;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.CandidatoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.transaction.Transactional;

@Tag("integration")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationTests.class)
//@Sql(value = "classpath:scripts/insere_unidade_adm.sql")
class CandidatoIT {

    private static final String API_PATH = "/api/v1/candidatos";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Test
    @DisplayName("Deve cadastrar uma fazenda corretamente")
    void cadastrar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(API_PATH))
//                .contentType(MediaType.APPLICATION_JSON))
//                .characterEncoding("UTF-8")
//                .content(objectMapper.writeValueAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}