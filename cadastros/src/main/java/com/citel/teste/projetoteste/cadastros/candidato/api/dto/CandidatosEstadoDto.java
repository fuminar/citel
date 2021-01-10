package com.citel.teste.projetoteste.cadastros.candidato.api.dto;

import com.citel.teste.projetoteste.cadastros.endereco.dominio.EEstado;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidatosEstadoDto {

    private EEstado estado;
    private Long numeroCandidatos;

    public static CandidatosEstadoDto from(EEstado estado, Long numeroCandidatos) {
        return new CandidatosEstadoDto(estado, numeroCandidatos);
    }

}
