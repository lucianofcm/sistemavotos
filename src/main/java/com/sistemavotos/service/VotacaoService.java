package com.sistemavotos.service;

import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.dto.ResultadoVotacaoDTO;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.exception.BasicException;

public interface VotacaoService {

	String votar(VotacaoDTO votacao) throws BasicException;
	ResultadoVotacaoDTO resultadoVotacao(Integer idPauta);	
	DuracaoVotacaoDTO iniciarVotacao(DuracaoVotacaoDTO duracaoVotacao);
	
}