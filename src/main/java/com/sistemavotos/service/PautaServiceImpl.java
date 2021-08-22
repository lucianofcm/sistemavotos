package com.sistemavotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.enumeration.EnumAtiva;
import com.sistemavotos.exception.PautaException;
import com.sistemavotos.repository.PautaRepository;

/**
 * Classe PautaServiceImpl.
 */
@Service
public class PautaServiceImpl implements PautaService {

	/** Pauta repository. */
	@Autowired
	private PautaRepository pautaRepository;

	/**
	 * Cadastrar pauta.
	 *
	 * @param pauta pauta que será cadastrada
	 * @return pauta que foi persistida
	 */
	@Override
	public Pauta cadastrarPauta(Pauta pauta) {
		return pautaRepository.save(pauta);
	}

	/**
	 * Localizar pauta por ID.
	 *
	 * @param id  parametro utilizdo para localizar a pauta
	 * @return pauta cadastrada no banco
	 * @throws lança exceção caso a pauta não seja localizada
	 */
	@Override
	public Pauta localizarPautaPorID(Integer id) {
		return pautaRepository.findById(id).orElseThrow(() -> new PautaException("Não foi possível encontrar a pauta"));
	}
	
	/**
	 * Verifica se a pauta já foi encerrada.
	 *
	 * @param pauta  pauta sendo que está sendo utilizda
	 * @return retorna true se a pauta já foi encerrada.
	 */
	public Boolean pautaEncerrada(Pauta pauta) {
		return pautaRepository.getById(pauta.getId()).getIndAtiva().equals(EnumAtiva.N);
	}

}
