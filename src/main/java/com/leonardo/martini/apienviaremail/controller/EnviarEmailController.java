package com.leonardo.martini.apienviaremail.controller;

import com.leonardo.martini.apienviaremail.dto.domain.MensagemSucessoDomain;
import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnviarEmailController {

    @PostMapping
    public ResponseEntity<String> enviar(@RequestBody EnviarEmailRequest request) {
        return ResponseEntity.ok(MensagemSucessoDomain.ENVIAR_EMAIL);
    }

}
