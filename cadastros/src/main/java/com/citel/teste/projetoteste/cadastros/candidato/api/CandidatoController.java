package com.citel.teste.projetoteste.cadastros.candidato.api;

import com.citel.teste.projetoteste.cadastros.candidato.api.dto.*;
import com.citel.teste.projetoteste.cadastros.candidato.api.exceptions.CandidatoNaoEncontradoException;
import com.citel.teste.projetoteste.cadastros.candidato.api.validacoes.IdadeValidator;
import com.citel.teste.projetoteste.cadastros.candidato.api.validacoes.PesoValidator;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.Candidato;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.CandidatoRepository;
import com.citel.teste.projetoteste.cadastros.candidato.dominio.ESexo;
import com.citel.teste.projetoteste.cadastros.endereco.dominio.EEstado;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping(path = "/api/v1/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoRepository repository;
    private final IdadeValidator idadeValidator;
    private final PesoValidator pesoValidator;

    @InitBinder("candidatoRequest")
    public void initBinders(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(idadeValidator);
        webDataBinder.addValidators(pesoValidator);
    }

    @PostMapping
    @Transactional
    @ApiOperation("Cadastra um Candidato")
    public ResponseEntity<CandidatoDto> cadastrar(@Valid @RequestBody CandidatoRequest candidatoRequest) {
        Candidato candidato = candidatoRequest.toModel();
        repository.save(candidato);
        return ResponseEntity.ok(CandidatoDto.from(candidato));
    }

    @PutMapping(path = "/{id}")
    @Transactional
    @ApiOperation("Altera um Candidato")
    public ResponseEntity<CandidatoDto> alterar(@PathVariable("id") UUID id,
                                                @Valid @RequestBody CandidatoRequest candidatoRequest) {
        if (!repository.existsById(id)) {
            throw new CandidatoNaoEncontradoException(id);
        }
        Candidato candidato = candidatoRequest.toModel();
        candidato.setId(id);
        repository.save(candidato);
        return ResponseEntity.ok(CandidatoDto.from(candidato));
    }

    @GetMapping("/candidatos-por-estado")
    @ApiOperation("Retorna lista de candidatos por estado")
    public ResponseEntity<List<CandidatosEstadoDto>> listarPorEstado() {
        List<CandidatosEstadoDto> candidatosEstado = EnumSet.allOf(EEstado.class).stream()
                .map(estado -> {
                    var numeroCandidatos = repository.countAllByEstado(estado);
                    return CandidatosEstadoDto.from(estado, numeroCandidatos);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(candidatosEstado);
    }

    @GetMapping("/imc-medio")
    @ApiOperation("Retorna IMC médio em cada faixa de idade")
    public ResponseEntity<List<FaixaIdadeImcDto>> listarPorFaixaEtaria() {
        return ResponseEntity.ok(CalculaMediaImcPorFaixaIdade.calcular(repository));
    }

    @GetMapping("/percentual-obesos")
    @ApiOperation("Retorna percentual de obesos entre homens e mulheres")
    public ResponseEntity<List<PercentualObesoDto>> listaPercentualObesos() {
        List<PercentualObesoDto> percentualObesos = EnumSet.allOf(ESexo.class).stream()
                .map(eSexo -> {
                    var totalObesos = repository.countByImcLessThanAndSexo(BigDecimal.valueOf(30), eSexo);
                    var total = repository.countBySexo(eSexo);
                    return PercentualObesoDto.from(eSexo, totalObesos, total);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(percentualObesos);
    }

    @GetMapping("/media-idade-tipo-sanguineo")
    @ApiOperation("Retorna média de idade para cada tipo sanguíneo")
    public ResponseEntity<List<MediaIdadePorTipoSanguineoDto>> listaMediaIdadePorTipoSanguineo() {
        return ResponseEntity.ok(CalculaMediaIdadePorTipoSanguineo.calcular(repository));
    }

    @GetMapping("/quantidade-possiveis-doadores")
    @ApiOperation("Retorna média de idade para cada tipo sanguíneo")
    public ResponseEntity<List<QuantidadeDoadoresPorTipoSanguineoDto>> listaQuantidadeDoadoresPorTipoSanguineo() {
        return ResponseEntity.ok(CalcularQuantidadeDoadoresDisponiveis.calcular(repository));
    }

}
