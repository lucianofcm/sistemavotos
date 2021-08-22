package com.sistemavotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavotos.domain.DuracaoVotacao;
import com.sistemavotos.repository.DuracaoVotacaoRepository;
import com.sistemavotos.repository.VotacaoRepository;

@Service
public class DuracaoVotacaoServiceImpl implements DuracaoVotacaoService {

	@Autowired
	PautaService pautaService;
	@Autowired
	private DuracaoVotacaoRepository duracaoVotacaoRepo;
	@Autowired
	private VotacaoRepository votacaoRepo;

	@Override
	public DuracaoVotacao gravarInicioVotacao(DuracaoVotacao duracaoVotacao) {
		return duracaoVotacaoRepo.save(DuracaoVotacao.builder().pauta(duracaoVotacao.getPauta())
				.tempoDuracao(duracaoVotacao.getTempoDuracao()).inicioVotacao(duracaoVotacao.getInicioVotacao())
				.fimVotacao(duracaoVotacao.getFimVotacao()).build());
	}

	@Override
	public void gravarFimVotacao(DuracaoVotacao duracaoVotacao) {
		// TODO Auto-generated method stub

	}

}
