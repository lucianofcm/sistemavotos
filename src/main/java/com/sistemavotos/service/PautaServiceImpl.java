package com.sistemavotos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.PautaDTO;
import com.sistemavotos.enumeration.EnumAtiva;
import com.sistemavotos.exception.BasicException;
import com.sistemavotos.exception.PautaException;
import com.sistemavotos.repository.PautaRepository;

/**
 * Classe responsável pela implementação dos serviços relacionado a entidade pauta.
 */
@Service
public class PautaServiceImpl implements PautaService {

	/** Pauta repository. */
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Metodo reponsável por cadastrar uma pauta.
	 *
	 * @param pauta pauta que será cadastrada
	 * @return pauta cadastrasta
	 */
	@Override
	public Pauta cadastrarPauta(Pauta pauta) {
		if (pauta.getTitulo() != null && pauta.getDescricao() != null) {
			return pautaRepository.save(pauta);
		} else {
			throw new BasicException("Os campos título e descrição sáo obrigatórios.");
		}
	}

	/**
	 * Localizar pauta por ID.
	 *
	 * @param id parametro utilizdo para localizar a pauta
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
	 * @param pauta pauta sendo que está sendo utilizda
	 * @return retorna true se a pauta já foi encerrada.
	 */
	public Boolean pautaEncerrada(Pauta pauta) {
		return pautaRepository.getById(pauta.getId()).getIndAtiva().equals(EnumAtiva.N);
	}
	
	@Override
	public List<PautaDTO> listarPautas() {
		return pautaRepository.findAll().stream().map(PautaDTO::new).collect(Collectors.toList());
	}

}
