package com.leonardo.martini.apienviaremail.integration;

import com.leonardo.martini.apienviaremail.dto.domain.MensagemErroDomain;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonsEmailIntegration implements EnviarEmailIntegration {

    @Value("${commons-email.host-name}")
    private String hostName;

    @Value("${commons-email.smtp-port}")
    private int smtpPort;

    @Value("${commons-email.authenticator.usuario}")
    private String usuario;

    @Value("${commons-email.authenticator.senha}")
    private String senha;

    @Value("${commons-email.remetente.email}")
    private String emailRemetete;

    @Value("${commons-email.remetente.nome}")
    private String nomeRemetete;

    @Override
    public void enviar(EnviarEmailRequest request) {
        SimpleEmail email = construirEmail(request);

        log.info("Enviando email.");

        try {
            email.send();
        } catch (EmailException exception) {
            throw falhaEnviarEmail(request, exception);
        }

        log.info("Email enviado com sucesso.");
    }

    private SimpleEmail construirEmail(EnviarEmailRequest request) {
        log.debug("Construindo email: {}", request);

        SimpleEmail email = new SimpleEmail();

        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthenticator(new DefaultAuthenticator(usuario, senha));
        email.setSubject(request.getAssunto());

        try {
            email.setFrom(emailRemetete, nomeRemetete);
            email.addTo(request.getEmail(), request.getNome());
            email.setMsg(request.getAssunto());
        } catch (EmailException exception) {
            throw falhaEnviarEmail(request, exception);
        }

        return email;
    }

    private RuntimeException falhaEnviarEmail(EnviarEmailRequest request, EmailException exception) {
        log.error("Falha ao enviar email: {}", request, exception);

        return new RuntimeException(MensagemErroDomain.ENVIAR_EMAIL);
    }

}
