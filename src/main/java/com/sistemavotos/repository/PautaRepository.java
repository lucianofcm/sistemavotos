package com.sistemavotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistemavotos.domain.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {
	
	@Query(value = "select p.* from pauta p left outer join duracao_votacao dv on p.id = dv.pauta_id where dv.inicio_votacao is null", nativeQuery = true)
	List<Pauta> listarPautaNaoIniciadas();	
	 

}
