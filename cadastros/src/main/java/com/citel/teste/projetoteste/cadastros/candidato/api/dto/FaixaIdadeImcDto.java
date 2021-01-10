package com.citel.teste.projetoteste.cadastros.candidato.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaixaIdadeImcDto {

    private static final Integer DEZ = 10;

    private String faixaIdade;
    private double imcMedio;

}
