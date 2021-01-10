package com.citel.teste.projetoteste.cadastros.candidato.api.dto;

import com.citel.teste.projetoteste.cadastros.candidato.dominio.ESexo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PercentualObesoDto {

    ESexo genero;
    double percentualObesos;

    public static PercentualObesoDto from(ESexo genero, Long totalObesos, Long total) {
        var percentualObesos = total / totalObesos * 100;
        return new PercentualObesoDto(genero, percentualObesos);
    }

}
