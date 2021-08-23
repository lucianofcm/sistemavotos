package com.sistemavotos.service;

import java.util.List;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.PautaDTO;

public interface PautaService {
	
	public Pauta cadastrarPauta(Pauta pauta);
	public Pauta localizarPautaPorID(Integer id);
	public Boolean pautaEncerrada(Pauta pauta);
	public List<PautaDTO> listarPautas();

}