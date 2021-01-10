package com.citel.teste.projetoteste.cadastros.candidato.api.dto;

import com.citel.teste.projetoteste.cadastros.candidato.dominio.Candidato;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ESexo;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ETipoSanguineo;
import com.citel.teste.projetoteste.cadastros.endereco.dominio.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDto {

    private UUID id;
    private String nome;
    private String nomeMae;
    private String nomePai;
    private String email;
    private Endereco endereco;
    private String telefoneFixo;
    private String celular;
    private String cpf;
    private String rg;
    private BigDecimal altura;
    private BigDecimal peso;
    private BigDecimal imc;
    private ETipoSanguineo tipoSanguineo;
    private ESexo sexo;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;

    public static CandidatoDto from(Candidato candidato) {
        return CandidatoDto.builder()
                .id(candidato.getId())
                .nome(candidato.getNome())
                .nomeMae(candidato.getNomeMae())
                .nomePai(candidato.getNomePai())
                .email(candidato.getEmail())
                .endereco(candidato.getEndereco())
                .telefoneFixo(candidato.getTelefoneFixo())
                .celular(candidato.getCelular())
                .cpf(candidato.getCpf())
                .rg(candidato.getRg())
                .altura(candidato.getAltura())
                .peso(candidato.getPeso())
                .imc(candidato.getImc())
                .tipoSanguineo(candidato.getTipoSanguineo())
                .sexo(candidato.getSexo())
                .dataNascimento(candidato.getDataNascimento())
                .dataCadastro(candidato.getDataCadastro())
                .build();
    }

}
