package com.citel.teste.projetoteste.cadastros.candidato.dominio;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.EnumSet;

public enum ESexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    @Getter
    private String value;

    ESexo(String value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ESexo getValue(String value) {
        return EnumSet.allOf(ESexo.class).stream()
                .filter(t -> t.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

}