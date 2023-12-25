package com.leonardo.martini.apienviaremail.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class EnviarEmailRequest {

    String nome;
    String email;
    String assunto;

}
