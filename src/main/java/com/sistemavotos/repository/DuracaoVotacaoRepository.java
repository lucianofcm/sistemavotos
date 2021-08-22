package com.sistemavotos.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistemavotos.domain.DuracaoVotacao;
import com.sistemavotos.domain.Pauta;

@Repository
public interface DuracaoVotacaoRepository extends JpaRepository<DuracaoVotacao, Integer> {

	@Query("select d from DuracaoVotacao d where d.pauta.id = :idPauta and d.inicioVotacao is not null")
	DuracaoVotacao localizarPorPautaNaoIniciada(@Param("idPauta")Integer idPauta);
	
	
	DuracaoVotacao findByPautaAndFimVotacaoBefore(Pauta pauta,LocalDateTime fimVotacao);
	
	/*
	 * @Query("select d from DuracaoVotacao d where d.pauta.id = :idPauta and d.fimVotacao < "
	 * ) DuracaoVotacao localizarPorPautaFimVotacao(@Param("idPauta")Integer
	 * idPauta);
	 */
	
}
