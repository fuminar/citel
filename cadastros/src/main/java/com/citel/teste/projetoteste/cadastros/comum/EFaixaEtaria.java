package com.citel.teste.projetoteste.cadastros.comum;

import lombok.Getter;

public enum EFaixaEtaria {

    DEZ(10, "11 a 20"),
    VINTE(20, "21 a 30"),
    TRINTA(30, "31 a 40"),
    QUARENTA(40, "41 a 50"),
    CINQUENTA(50, "51 a 60"),
    SESSENTA(60, "61 a 70");

    @Getter
    private final Integer value;
    @Getter
    private final String decription;

    EFaixaEtaria(Integer value, String decription) {
        this.value = value;
        this.decription = decription;
    }

}
