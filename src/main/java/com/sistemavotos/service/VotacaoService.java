package com.sistemavotos.service;

import com.sistemavotos.domain.Votacao;
import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.exception.BasicException;

public interface VotacaoService {

	String votar(VotacaoDTO votacao) throws BasicException;
	void encerrarVotacao(Votacao votacao);
	void resultadoVotacao(Votacao votacao);	
	void iniciarVotacao(DuracaoVotacaoDTO duracaoVotacao);
	
}