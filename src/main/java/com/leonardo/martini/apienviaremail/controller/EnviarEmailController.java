package com.leonardo.martini.apienviaremail.controller;

import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;
import com.leonardo.martini.apienviaremail.dto.response.EnviarEmailResponse;
import com.leonardo.martini.apienviaremail.service.EnviarEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnviarEmailController {

    @Autowired
    private EnviarEmailService service;

    @PostMapping
    public EnviarEmailResponse enviar(@RequestBody EnviarEmailRequest request) {
        return service.enviar(request);
    }

}
