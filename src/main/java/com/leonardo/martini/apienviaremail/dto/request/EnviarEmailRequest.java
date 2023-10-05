package com.leonardo.martini.apienviaremail.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class EnviarEmailRequest {

    String nome;
    String email;
    String assunto;

}
