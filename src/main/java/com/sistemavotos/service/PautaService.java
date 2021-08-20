package com.sistemavotos.service;

import com.sistemavotos.domain.Pauta;

public interface PautaService {
	
	public Pauta cadastrarPauta(Pauta pauta);
	public Pauta localizarPautaPorID(Integer id);

}