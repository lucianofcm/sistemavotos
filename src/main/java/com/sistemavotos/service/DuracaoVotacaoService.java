package com.sistemavotos.service;

import com.sistemavotos.domain.DuracaoVotacao;

public interface DuracaoVotacaoService {
	
	public DuracaoVotacao gravarInicioVotacao(DuracaoVotacao duracaoVotacao);
	public DuracaoVotacao localizarPorPauta(Integer idPauta);

}