package com.leonardo.martini.apienviaremail.integration;

import com.leonardo.martini.apienviaremail.dto.request.EnviarEmailRequest;

public interface EnviarEmailIntegration {

    void enviar(EnviarEmailRequest request);

}
