package com.sistemavotos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistemavotos.domain.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Integer> {

	@Query("select v from Votacao v join v.pauta p where v.cpfUsuario = :cpfUsuario and p.id = :idPauta")
	Optional<Votacao> localizarPorCpfEPauta(@Param("cpfUsuario") String cpfUsuario, @Param("idPauta") Integer idPauta);
	
	@Query("select v from Votacao v join v.pauta p where p.id = :idPauta")
	List<Votacao> listarVotacaoPorPauta(@Param("idPauta") Integer idPauta);

}
