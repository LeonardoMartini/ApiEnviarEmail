package com.leonardo.martini.apienviaremail.service;

import com.leonardo.martini.apienviaremail.dto.domain.MensagemErroDomain;
import com.leonardo.martini.apienviaremail.dto.exception.DadoInvalidoException;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import com.leonardo.martini.apienviaremail.integration.EnviarEmailIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnviarEmailService {

    private final EnviarEmailIntegration integration;

    public void enviar(EnviarEmailRequest request) {
        validarEmail(request);

        log.info("Executando fluxo de envio de email.");

        integration.enviar(request);
    }

    private void validarEmail(EnviarEmailRequest request) {
        if (request == null) {
            log.error("Falha ao enviar email. Requisição nula.");

            throw new DadoInvalidoException(MensagemErroDomain.REQUISICAO_NULA);
        }
        if (isBlank(request.getEmail()) || !request.getEmail().matches("^(.+)@(.+)$")) {
            log.error("Falha ao enviar email. Email inválido.");

            throw new DadoInvalidoException(MensagemErroDomain.EMAIL_INVALIDO);
        }
        if (isBlank(request.getNome())) {
            log.error("Falha ao enviar email. Nome inválido.");

            throw new DadoInvalidoException(MensagemErroDomain.NOME_INVALIDO);
        }
        if (isBlank(request.getAssunto())) {
            log.error("Falha ao enviar email. Assunto inválido.");

            throw new DadoInvalidoException(MensagemErroDomain.ASSUNTO_INVALIDO);
        }
    }

}
