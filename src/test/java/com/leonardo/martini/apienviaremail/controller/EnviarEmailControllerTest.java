package com.leonardo.martini.apienviaremail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EnviarEmailController.class)
public class EnviarEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void quandoReceberRequestValidaDeveRetornarSucesso() throws Exception {
        EnviarEmailRequest request = new EnviarEmailRequest("Teste", "teste@teste.com", "Teste");

        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


}
