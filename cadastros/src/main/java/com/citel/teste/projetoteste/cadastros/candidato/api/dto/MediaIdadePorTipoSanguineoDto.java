package com.citel.teste.projetoteste.cadastros.candidato.api.dto;

import com.citel.teste.projetoteste.cadastros.candidato.dominio.ETipoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaIdadePorTipoSanguineoDto {

    private ETipoSanguineo tipoSanguineo;
    private Long idade;

}
