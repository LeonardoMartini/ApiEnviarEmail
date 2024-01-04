package com.leonardo.martini.apienviaremail.dto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AbstractErrorException extends RuntimeException {

    private final HttpStatus httpStatus;

    protected AbstractErrorException(HttpStatus httpStatus, String mensagem) {
        super(mensagem);
        this.httpStatus = httpStatus;
    }

}
