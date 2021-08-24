package com.sistemavotos.rules;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.exception.BasicException;
import com.sistemavotos.exception.UsuarioJaVotouException;
import com.sistemavotos.exception.VotacaoEncerradaException;
import com.sistemavotos.repository.DuracaoVotacaoRepository;
import com.sistemavotos.repository.VotacaoRepository;
import com.sistemavotos.service.ValidacaoCpfService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VotacaoRule {

	@Autowired
	private VotacaoRepository votacaoRepo;
	@Autowired
	private ValidacaoCpfService validacaoCpfService;
	@Autowired
	private DuracaoVotacaoRepository duracaoVotacaoRepository;

	/**
	 * Método responsável por verificar se o usuário já votou, caso já tenha não pode votar mais.
	 *
	 * @param votacao deve ser informada para que seja verificada se o usuário já votou
	 * @return VotacaoRule a própria classe
	 */
	public VotacaoRule usuarioJaVotou(VotacaoDTO votacao) {
		 log.info("Verificando se o usuário já votou.");
		if (votacaoRepo.localizarPorCpfEPauta(votacao.getCpfUsuario(), votacao.getPauta().getId()).isPresent()) {
			throw new UsuarioJaVotouException("Usuário já votou nesta pauta");
		}
		return this;
	}

	/**
	 * Método responsável por verificar se o usuário pode votar através da consultado no webservice
	 *
	 * @param numCpf número do cpf do usuário que está votando
	 * @return VotacaoRule a própria classe
	 */
	public VotacaoRule usuarioNaoPodeVota(String numCpf){
		 log.info("Verificando se o usuário pode votar");
		validacaoCpfService.usuarioPodeVotar(numCpf);
		return this;
	}

	/**
	 * Método responsável por verificar se a votação já foi inciada, caso não tenha sido não é possível votar.
	 *
	 * @param idPauta o identificador da pauta que está associada com a votação
	 * @return VotacaoRule a própria classe
	 */
	public VotacaoRule votacaoJaIniciada(Integer idPauta) throws BasicException {
		 log.info("verificando se a votação já foi iniciada.");
		if (duracaoVotacaoRepository.localizarPorPautaNaoIniciada(idPauta) != null) {
			throw new BasicException("Esta votacao já foi iniciada");
		}
		return this;
	}

	/**
	 * Método responsável por verificar se a votação já foi encerrada, caso já tenha sido não é possível votar.
	 *
	 * @param pauta the pauta
	 * @return the votacao rule
	 * @throws VotacaoEncerradaException the votacao encerrada exception
	 */
	public VotacaoRule votacaoEncerrada(Pauta pauta) throws VotacaoEncerradaException {
		log.info("verificando se a votação já foi encerrada.");
		if (duracaoVotacaoRepository.findByPautaAndFimVotacaoBefore(pauta, LocalDateTime.now()) != null) {
			throw new VotacaoEncerradaException("Votação já encerrada.");
		}
		return this;
	}

	/**
	 * Método responsável por verificar se a votação já foi iniciada
	 *
	 * @param pauta entidade pauta
	 * @return a propria classe
	 * @throws VotacaoEncerradaException the votacao encerrada exception
	 */
	public VotacaoRule votacaoNaoIniciada(Pauta pauta) throws VotacaoEncerradaException {
		log.info("Verificando se a votação foi iniciada.");
		if (duracaoVotacaoRepository.localizarPorPautaNaoIniciada(pauta.getId()) == null) {
			throw new VotacaoEncerradaException("Votação não iniciada.");
		}
		return this;
	}

}
