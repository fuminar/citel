package com.citel.teste.projetoteste.cadastros.candidato.dominio;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.EnumSet;

public enum ETipoSanguineo {

    A_POS("A+"),
    A_NEG("A-"),
    B_POS("B+"),
    B_NEG("B-"),
    AB_POS("AB+"),
    AB_NEG("AB-"),
    O_POS("O+"),
    O_NEG("O-"),
    DESCONHECIDO("NA");

    @Getter
    private final String value;

    ETipoSanguineo(String value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ETipoSanguineo getValue(String value) {
        return EnumSet.allOf(ETipoSanguineo.class).stream()
                .filter(t -> t.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(ETipoSanguineo.DESCONHECIDO);
    }

}
