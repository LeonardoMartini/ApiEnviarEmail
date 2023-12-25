package com.leonardo.martini.apienviaremail.service;

import com.leonardo.martini.apienviaremail.dto.domain.MensagemErroDomain;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import com.leonardo.martini.apienviaremail.dto.response.EnviarEmailResponse;
import com.leonardo.martini.apienviaremail.integration.EnviarEmailIntegration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class EnviarEmailServiceTest {

    @InjectMocks
    private EnviarEmailService service;

    @Mock
    private EnviarEmailIntegration integration;

    @Test
    public void deveEnviarEmailComSucesso() {
        EnviarEmailRequest request = EnviarEmailRequest.builder()
                .nome("Nome Teste")
                .email("email@teste.com")
                .build();

        EnviarEmailResponse response = service.enviar(request);

        assertNotNull(response);
        assertNotNull(response.getMensagem());

        verify(integration).enviar(any());
    }

    @Test
    public void deveRetornarErroQuandoRequisicaoNula() {
        EnviarEmailRequest request = null;

        try {
            service.enviar(request);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), MensagemErroDomain.REQUISICAO_NULA);
        } finally {
            verifyNoInteractions(integration);
        }
    }

    @Test
    public void deveRetornarErroQuandoEmailNulo() {
        EnviarEmailRequest request = EnviarEmailRequest.builder().build();

        try {
            service.enviar(request);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), MensagemErroDomain.EMAIL_INVALIDO);
        } finally {
            verifyNoInteractions(integration);
        }
    }

    @Test
    public void deveRetornarErroQuandoEmailVazio() {
        EnviarEmailRequest request = EnviarEmailRequest.builder()
                .email("")
                .build();

        try {
            service.enviar(request);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), MensagemErroDomain.EMAIL_INVALIDO);
        } finally {
            verifyNoInteractions(integration);
        }
    }

    @Test
    public void deveRetornarErroQuandoEmailInvalido() {
        EnviarEmailRequest request = EnviarEmailRequest.builder()
                .email("email invalido")
                .build();

        try {
            service.enviar(request);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), MensagemErroDomain.EMAIL_INVALIDO);
        } finally {
            verifyNoInteractions(integration);
        }
    }

    @Test
    public void deveRetornarErroQuandoEmailNomeNulo() {
        EnviarEmailRequest request = EnviarEmailRequest.builder()
                .email("email@valido.com")
                .build();

        try {
            service.enviar(request);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), MensagemErroDomain.NOME_INVALIDO);
        } finally {
            verifyNoInteractions(integration);
        }
    }

    @Test
    public void deveRetornarErroQuandoEmailNomeVazio() {
        EnviarEmailRequest request = EnviarEmailRequest.builder()
                .email("email@valido.com")
                .nome("")
                .build();

        try {
            service.enviar(request);
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), MensagemErroDomain.NOME_INVALIDO);
        } finally {
            verifyNoInteractions(integration);
        }
    }

}
