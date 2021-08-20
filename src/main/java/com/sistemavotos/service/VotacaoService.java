package com.sistemavotos.service;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.domain.Usuario;
import com.sistemavotos.domain.Votacao;

public interface VotacaoService {

	public void votar(Pauta pauta,Usuario usario);
	public void encerrarVotacao(Votacao votacao);
	public void resultadoVotacao(Votacao votacao);
	
}