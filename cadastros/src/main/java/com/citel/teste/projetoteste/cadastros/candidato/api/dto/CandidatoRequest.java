package com.citel.teste.projetoteste.cadastros.candidato.api.dto;

import com.citel.teste.projetoteste.cadastros.candidato.dominio.Candidato;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ESexo;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ETipoSanguineo;
import com.citel.teste.projetoteste.cadastros.endereco.dominio.EEstado;
import com.citel.teste.projetoteste.cadastros.endereco.dominio.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CandidatoRequest {

    @Digits(integer = 5, fraction = 2)
    @NotNull(message = "{Candidato.peso.NotNull}")
    private final BigDecimal peso;

    @Digits(integer = 5, fraction = 2)
    @NotNull(message = "{Candidato.altura.NotNull}")
    private final BigDecimal altura;

    @NotNull(message = "{Candidato.tipoSanguineo.NotNull}")
    @JsonProperty("tipo_sanguineo")
    private final ETipoSanguineo tipoSanguineo;

    @NotBlank(message = "{Candidato.tipoSanguineo.NotBlank}")
    private final String nome;

    private final ESexo sexo;

    @NotBlank(message = "{Candidato.mae.NotBlank}")
    private final String mae;

    @NotBlank(message = "{Candidato.pai.NotBlank}")
    private final String pai;

    @NotBlank(message = "{Candidato.email.NotBlank}")
    private final String email;

    @NotBlank(message = "{Candidato.cep.NotBlank}")
    private final String cep;

    @NotBlank(message = "{Candidato.endereco.NotBlank}")
    private final String endereco;

    @NotBlank(message = "{Candidato.numero.NotBlank}")
    private final String numero;

    @NotBlank(message = "{Candidato.bairro.NotBlank}")
    private final String bairro;

    @NotBlank(message = "{Candidato.cidade.NotBlank}")
    private final String cidade;

    private final EEstado estado;

    @NotBlank(message = "{Candidato.telefoneFixo.NotBlank}")
    @JsonProperty("telefone_fixo")
    private final String telefoneFixo;

    @NotBlank(message = "{Candidato.celular.NotBlank}")
    private final String celular;

    @CPF
    @NotBlank(message = "{Candidato.cpf.NotBlank}")
    private final String cpf;

    @NotBlank(message = "{Candidato.rg.NotBlank}")
//    @RG
    private final String rg;

    @NotNull(message = "{Candidato.dataNascimento.NotBlank}")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("data_nasc")
    private final LocalDate dataNascimento;

    public Candidato toModel() {
        return Candidato.builder()
                .nome(nome)
                .nomeMae(mae)
                .nomePai(pai)
                .email(email)
                .endereco(buildEndereco())
                .telefoneFixo(telefoneFixo)
                .celular(celular)
                .cpf(cpf)
                .rg(rg)
                .altura(altura)
                .peso(peso)
                .imc(getImc())
                .tipoSanguineo(tipoSanguineo)
                .sexo(sexo)
                .dataNascimento(dataNascimento)
                .dataCadastro(LocalDateTime.now())
                .build();
    }

    private Endereco buildEndereco() {
        return Endereco.builder()
                .logradouro(endereco)
                .bairro(bairro)
                .cep(cep)
                .cidade(cidade)
                .estado(estado)
                .numero(numero)
                .build();
    }

    private BigDecimal getImc() {
        return peso.divide(altura.multiply(altura), 2, RoundingMode.HALF_EVEN);
    }

}