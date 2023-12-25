package com.leonardo.martini.apienviaremail.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EnviarEmailResponse {

    String mensagem;

}
