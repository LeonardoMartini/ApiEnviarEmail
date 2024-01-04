package com.leonardo.martini.apienviaremail.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {

    private final String message;
    private final int statusCode;
    private final String statusName;
    private final String path;
    private final String method;
    private final LocalDateTime timestamp;

}
