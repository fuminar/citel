package com.citel.teste.projetoteste.cadastros.candidato.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
public class CandidatoNaoEncontradoException extends RuntimeException {

    @Getter
    public final UUID id;

}
