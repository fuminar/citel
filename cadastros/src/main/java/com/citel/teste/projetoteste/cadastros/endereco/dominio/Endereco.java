package com.citel.teste.projetoteste.cadastros.endereco.dominio;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Endereco {

    @Id
    @GeneratedValue
    @Setter
    private UUID id;

    @NotNull
    private String logradouro;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String numero;

    @Enumerated(EnumType.STRING)
    private EEstado estado;

    @NotNull
    @Size(max = 10)
    private String cep;

}
