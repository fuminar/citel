package com.citel.teste.projetoteste.cadastros.candidato.api.validacoes;

import com.citel.teste.projetoteste.cadastros.candidato.api.dto.CandidatoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class PesoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CandidatoRequest.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CandidatoRequest request = (CandidatoRequest) target;
        if (request.getPeso().compareTo(BigDecimal.valueOf(50L)) < 0) {
            errors.reject("Somente candidatos com peso acima de 50 Kg podem doar sangue!",
                    new Object[]{request.getPeso()}, "");
        }
    }
}
