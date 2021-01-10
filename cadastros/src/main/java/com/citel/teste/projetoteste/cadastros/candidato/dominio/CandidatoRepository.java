package com.citel.teste.projetoteste.cadastros.candidato.dominio;

import com.citel.teste.projetoteste.cadastros.endereco.dominio.EEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {

    @Query(value = "SELECT COUNT(c) FROM Candidato c INNER JOIN c.endereco e WHERE e.estado = :estado")
    Long countAllByEstado(EEstado estado);

    List<Candidato> findAllByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);

    Long countByImcLessThanAndSexo(BigDecimal imc, ESexo sexo);

    Long countBySexo(ESexo sexo);

    List<Candidato> findAllByTipoSanguineo(ETipoSanguineo tipoSanguineo);

    Long countByTipoSanguineo(ETipoSanguineo tipoSanguineo);

}
