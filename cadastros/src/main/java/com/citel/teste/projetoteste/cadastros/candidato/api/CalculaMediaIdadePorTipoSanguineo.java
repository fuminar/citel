package com.citel.teste.projetoteste.cadastros.candidato.api;

import com.citel.teste.projetoteste.cadastros.candidato.api.dto.MediaIdadePorTipoSanguineoDto;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.Candidato;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.CandidatoRepository;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ETipoSanguineo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalculaMediaIdadePorTipoSanguineo {

    private static final LocalDate hoje = LocalDate.now();

    public static List<MediaIdadePorTipoSanguineoDto> calcular(CandidatoRepository candidatoRepository) {
        return EnumSet.allOf(ETipoSanguineo.class).stream()
                .map(eTipoSanguineo -> {
                    List<Candidato> candidatosList = candidatoRepository.findAllByTipoSanguineo(eTipoSanguineo);
                    var mediaIdade = candidatosList.stream()
                            .mapToDouble(candidato -> Period.between(candidato.getDataNascimento(), hoje).getYears())
                            .average()
                            .orElse(Double.NaN);
                    return new MediaIdadePorTipoSanguineoDto(eTipoSanguineo, Math.round(mediaIdade));
                })
                .collect(Collectors.toList());
    }

}
