package com.leonardo.martini.apienviaremail.service;

import com.leonardo.martini.apienviaremail.dto.domain.MensagemErroDomain;
import com.leonardo.martini.apienviaremail.dto.domain.MensagemSucessoDomain;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import com.leonardo.martini.apienviaremail.dto.response.EnviarEmailResponse;
import com.leonardo.martini.apienviaremail.integration.EnviarEmailIntegration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Slf4j
@Service
public class EnviarEmailService {

    @Autowired
    private EnviarEmailIntegration integration;

    public EnviarEmailResponse enviar(EnviarEmailRequest request) {
        validarEmail(request);

        log.info("Executando fluxo de envio de email.");

        integration.enviar(request);

        return EnviarEmailResponse.builder()
                .mensagem(MensagemSucessoDomain.ENVIAR_EMAIL)
                .build();
    }

    private void validarEmail(EnviarEmailRequest request) {
        if (request == null) {
            log.error("Falha ao enviar email. Requisição nula.");

            throw new RuntimeException(MensagemErroDomain.REQUISICAO_NULA);
        }
        if (isBlank(request.getEmail()) || !request.getEmail().matches("^(.+)@(.+)$")) {
            log.error("Falha ao enviar email. Email inválido.");

            throw new RuntimeException(MensagemErroDomain.EMAIL_INVALIDO);
        }
        if (isBlank(request.getNome())) {
            log.error("Falha ao enviar email. Nome inválido.");

            throw new RuntimeException(MensagemErroDomain.NOME_INVALIDO);
        }
    }

}
