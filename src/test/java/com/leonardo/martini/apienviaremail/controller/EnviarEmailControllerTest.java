package com.leonardo.martini.apienviaremail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.martini.apienviaremail.dto.domain.MensagemSucessoDomain;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import com.leonardo.martini.apienviaremail.dto.response.EnviarEmailResponse;
import com.leonardo.martini.apienviaremail.service.EnviarEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EnviarEmailController.class)
public class EnviarEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnviarEmailService service;

    @Test
    void quandoReceberRequestValidaDeveRetornarSucesso() throws Exception {
        EnviarEmailRequest request = new EnviarEmailRequest("Teste", "teste@teste.com", "Teste");

        when(service.enviar(any())).thenReturn(EnviarEmailResponse.builder().mensagem(MensagemSucessoDomain.ENVIAR_EMAIL).build());

        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


}
