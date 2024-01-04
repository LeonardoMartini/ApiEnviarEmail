package com.leonardo.martini.apienviaremail.dto.exception;

import org.springframework.http.HttpStatus;

public class ErroInternoException extends AbstractErrorException {

    public ErroInternoException(String mensagem) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, mensagem);
    }

}
