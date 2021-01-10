package com.citel.teste.projetoteste.cadastros.candidato.dominio;

import com.citel.teste.projetoteste.cadastros.endereco.dominio.Endereco;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Candidato {

    @Id
    @GeneratedValue
    @Setter
    private UUID id;

    @NotNull
    private String nome;

    @NotNull
    private String nomeMae;

    @NotNull
    private String nomePai;

    @NotNull
    private String email;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Endereco endereco;

    @NotNull
    private String telefoneFixo;

    @NotNull
    private String celular;

    @NotNull
    private String cpf;

    @NotNull
    private String rg;

    @NotNull
    private BigDecimal altura;

    @NotNull
    private BigDecimal peso;

    @NotNull
    private BigDecimal imc;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ETipoSanguineo tipoSanguineo;

    @Enumerated(EnumType.STRING)
    private ESexo sexo;

    @NotNull
    private LocalDate dataNascimento;

    private LocalDateTime dataCadastro;

}
