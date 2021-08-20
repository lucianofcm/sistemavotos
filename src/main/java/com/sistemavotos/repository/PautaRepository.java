package com.sistemavotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemavotos.domain.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}
