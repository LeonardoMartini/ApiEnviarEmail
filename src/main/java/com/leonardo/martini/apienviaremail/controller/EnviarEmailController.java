package com.leonardo.martini.apienviaremail.controller;

import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import com.leonardo.martini.apienviaremail.service.EnviarEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnviarEmailController {

    private final EnviarEmailService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void enviar(@RequestBody EnviarEmailRequest request) {
        service.enviar(request);
    }

}
