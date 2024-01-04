package com.leonardo.martini.apienviaremail.dto.exception;

import org.springframework.http.HttpStatus;

public class DadoInvalidoException extends AbstractErrorException {

    public DadoInvalidoException(String mensagem) {
        super(HttpStatus.BAD_REQUEST, mensagem);
    }

}
