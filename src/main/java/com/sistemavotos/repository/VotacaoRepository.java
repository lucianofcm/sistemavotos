package com.sistemavotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemavotos.domain.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Integer> {

}
