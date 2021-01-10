package com.citel.teste.projetoteste.cadastros.candidato.api;

import com.citel.teste.projetoteste.cadastros.candidato.api.dto.QuantidadeDoadoresPorTipoSanguineoDto;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.CandidatoRepository;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ETipoSanguineo;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.citel.teste.projetoteste.cadastros.candidato.dominio.ETipoSanguineo.*;

@Component
public class CalcularQuantidadeDoadoresDisponiveis {

    public static List<QuantidadeDoadoresPorTipoSanguineoDto> calcular(CandidatoRepository candidatoRepository) {
        List<QuantidadeDoadoresPorTipoSanguineoDto> qtdGeralList = EnumSet.allOf(ETipoSanguineo.class).stream()
                .map(eTipoSanguineo -> {
                    var qtdTotal = candidatoRepository.countByTipoSanguineo(eTipoSanguineo);
                    return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdTotal);
                })
                .collect(Collectors.toList());

        return EnumSet.allOf(ETipoSanguineo.class).stream()
                .map(eTipoSanguineo -> {
                    long qtdDoadoresCompativeis;
                    switch (eTipoSanguineo) {
                        case A_POS:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaAPos)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case A_NEG:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaANeg)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case B_POS:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaBPos)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case B_NEG:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaBNeg)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case AB_POS:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaABPos)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case AB_NEG:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaABNeg)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case O_POS:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaOPos)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        case O_NEG:
                            qtdDoadoresCompativeis = qtdGeralList.stream()
                                    .map(QuantidadeDoadoresPorTipoSanguineoDto::getTipoSanguineo)
                                    .filter(CalcularQuantidadeDoadoresDisponiveis::isDoadorParaONeg)
                                    .count();
                            return new QuantidadeDoadoresPorTipoSanguineoDto(eTipoSanguineo, qtdDoadoresCompativeis);
                        default:
                            return new QuantidadeDoadoresPorTipoSanguineoDto(DESCONHECIDO, 0L);
                    }
                })
                .collect(Collectors.toList());
    }

    private static boolean isDoadorParaAPos(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(A_POS)
                || tipoSanguineo.equals(A_NEG)
                || tipoSanguineo.equals(O_POS)
                || tipoSanguineo.equals(O_NEG);
    }

    private static boolean isDoadorParaANeg(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(A_NEG) || tipoSanguineo.equals(O_NEG);
    }

    private static boolean isDoadorParaBPos(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(B_POS)
                || tipoSanguineo.equals(B_NEG)
                || tipoSanguineo.equals(O_POS)
                || tipoSanguineo.equals(O_NEG);
    }

    private static boolean isDoadorParaBNeg(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(B_NEG) || tipoSanguineo.equals(O_NEG);
    }

    private static boolean isDoadorParaABPos(ETipoSanguineo tipoSanguineo) {
        return true;
    }

    private static boolean isDoadorParaABNeg(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(A_NEG)
                || tipoSanguineo.equals(B_NEG)
                || tipoSanguineo.equals(O_NEG)
                || tipoSanguineo.equals(AB_NEG);
    }

    private static boolean isDoadorParaOPos(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(O_POS)
        || tipoSanguineo.equals(O_NEG);
    }

    private static boolean isDoadorParaONeg(ETipoSanguineo tipoSanguineo) {
        return tipoSanguineo.equals(O_NEG);
    }

}
