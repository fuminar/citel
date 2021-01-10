package com.citel.teste.projetoteste.cadastros.candidato.api.validacoes;

import com.citel.teste.projetoteste.cadastros.candidato.api.dto.CandidatoRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class IdadeValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return clazz == CandidatoRequest.class;
    }

    private final LocalDate hoje = LocalDate.now();
    private final LocalDate dezesseisAnos = hoje.minusYears(16L);
    private final LocalDate sessentaENove = hoje.minusYears(69L);

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        CandidatoRequest request = (CandidatoRequest) target;
        LocalDate dataNascimento = request.getDataNascimento();
        if (dataNascimento.isAfter(dezesseisAnos) || dataNascimento.isBefore(sessentaENove)) {
            errors.reject("Somente candidatos com idade de 16 a 69 anos!",
                    new Object[]{request.getDataNascimento()}, "");
        }
    }

}
