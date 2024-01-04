package com.leonardo.martini.apienviaremail.interceptor;

import com.leonardo.martini.apienviaremail.dto.domain.MensagemErroDomain;
import com.leonardo.martini.apienviaremail.dto.exception.AbstractErrorException;
import com.leonardo.martini.apienviaremail.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerErrorInterceptor {

    @ExceptionHandler(AbstractErrorException.class)
    public ResponseEntity<?> handleErroClienteException(final AbstractErrorException exception,
                                                        final HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                exception.getMessage(),
                exception.getHttpStatus().value(),
                exception.getHttpStatus().name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );

        log.error("Erro do cliente: {}", errorResponse, exception);

        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErroInternoException(final Exception exception,
                                                        final HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                MensagemErroDomain.ERRO_INTERNO,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );

        log.error("Erro interno: {}", errorResponse, exception);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
