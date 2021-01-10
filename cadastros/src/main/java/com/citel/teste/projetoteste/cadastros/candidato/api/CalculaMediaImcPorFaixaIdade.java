package com.citel.teste.projetoteste.cadastros.candidato.api;

import com.citel.teste.projetoteste.cadastros.candidato.api.dto.FaixaIdadeImcDto;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.Candidato;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.CandidatoRepository;
import com.citel.teste.projetoteste.cadastros.comum.EFaixaEtaria;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalculaMediaImcPorFaixaIdade {

    private static final LocalDate hoje = LocalDate.now();
    private static final Integer DEZ = 10;


    public static List<FaixaIdadeImcDto> calcular(CandidatoRepository candidatoRepository) {
        return EnumSet.allOf(EFaixaEtaria.class).stream()
                .map(eFaixaEtaria -> {
                    LocalDate idade = hoje.minusYears(eFaixaEtaria.getValue());
                    List<Candidato> candidatosList =
                            candidatoRepository.findAllByDataNascimentoBetween(idade.minusYears(DEZ), idade);
                    var imcMedio = candidatosList.stream()
                            .mapToDouble(candidato -> candidato.getImc().doubleValue())
                            .average()
                            .orElse(Double.NaN);
                    return new FaixaIdadeImcDto(eFaixaEtaria.getDecription(), imcMedio);
                })
                .collect(Collectors.toList());
    }

}
