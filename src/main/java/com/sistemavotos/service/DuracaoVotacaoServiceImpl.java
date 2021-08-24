package com.sistemavotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavotos.domain.DuracaoVotacao;
import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.repository.DuracaoVotacaoRepository;

@Service
public class DuracaoVotacaoServiceImpl implements DuracaoVotacaoService {

	@Autowired
	PautaService pautaService;
	@Autowired
	private DuracaoVotacaoRepository duracaoVotacaoRepo;
	
	/**
	 * Gravar inicio votacao
	 *
	 * @param duracaoVotacao informação sobre a duração da votação
	 * @return as informações sobre a duração da votação persistida. 
	 */
	@Override
	public DuracaoVotacao gravarInicioVotacao(DuracaoVotacao duracaoVotacao) {
		return duracaoVotacaoRepo.save(DuracaoVotacao.builder().pauta(duracaoVotacao.getPauta())
				.tempoDuracao(duracaoVotacao.getTempoDuracao()).inicioVotacao(duracaoVotacao.getInicioVotacao())
				.fimVotacao(duracaoVotacao.getFimVotacao()).build());
	}

	@Override
	public DuracaoVotacao localizarPorPauta(Integer idPauta) {
		return duracaoVotacaoRepo.lucalizarPorPautaId(idPauta);
	}

}
