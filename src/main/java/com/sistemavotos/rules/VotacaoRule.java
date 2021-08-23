package com.sistemavotos.rules;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemavotos.domain.DuracaoVotacao;
import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.enumeration.EnumAtiva;
import com.sistemavotos.exception.BasicException;
import com.sistemavotos.exception.PautaException;
import com.sistemavotos.exception.UsuarioJaVotouException;
import com.sistemavotos.exception.VotacaoEncerradaException;
import com.sistemavotos.repository.DuracaoVotacaoRepository;
import com.sistemavotos.repository.VotacaoRepository;
import com.sistemavotos.service.ValidacaoCpfService;

@Component
public class VotacaoRule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VotacaoRule.class);
	
	@Autowired
	private VotacaoRepository votacaoRepo;
	@Autowired
	private ValidacaoCpfService validacaoCpfService;
	@Autowired
	private DuracaoVotacaoRepository duracaoVotacaoRepository;
	

	public VotacaoRule usuarioJaVotou(VotacaoDTO votacao) {
		if (votacaoRepo.localizarPorCpfEPauta(votacao.getCpfUsuario(), votacao.getPauta().getId()).isPresent()) {
			throw new UsuarioJaVotouException("Usuário já votou nesta pauta");
		}
		return this;
	}
	
	public VotacaoRule usuarioNaoPodeVota(String numCpf) throws BasicException {
		validacaoCpfService.usuarioPodeVotar(numCpf);
		return this;
	}
	
	public VotacaoRule votacaoJaIniciada(Integer idPauta) throws BasicException {
		if(duracaoVotacaoRepository.localizarPorPautaNaoIniciada(idPauta) != null) {
			throw new BasicException("Esta votacao já foi iniciada");
		}
		return this;
	}
	
	public VotacaoRule votacaoEncerrada(Pauta pauta) throws VotacaoEncerradaException {
		if(duracaoVotacaoRepository.findByPautaAndFimVotacaoBefore(pauta, LocalDateTime.now()) != null) {
			throw new VotacaoEncerradaException("Votação já encerrada.");
		}
		return this;
	}
	
	public VotacaoRule votacaoNaoIniciada(Pauta pauta) throws VotacaoEncerradaException {
		if(duracaoVotacaoRepository.localizarPorPautaNaoIniciada(pauta.getId()) == null){
			throw new VotacaoEncerradaException("Votação não iniciada.");
		}
		return this;
	}
	
}
