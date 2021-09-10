package com.sistemavotos.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.messageria.VotacaoSenderService;

/**
 * The Class AgendadorService.
 */
@Service
public class AgendadorService {

	private static final long TEMPO_PADRAO_DURACAO = 2l;

	private VotacaoSenderService votacaoSenderService;
	private VotacaoService votacaoService;

	private DuracaoVotacaoDTO duracaoVotacao;

	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public AgendadorService(VotacaoSenderService votacaoSenderService, VotacaoService votacaoService) {
		this.votacaoSenderService = votacaoSenderService;
		this.votacaoService = votacaoService;
	}


	/** Envia para o serviço de mensageria o resultdo após o término da votação */
	Runnable task = () -> votacaoSenderService
			.sendPautaEncerrada(votacaoService.resultadoVotacao(duracaoVotacao.getPauta().getId()));

	/**
	 * Executa o agendamento a partir das informações passadas.
	 *
	 * @param duracaoVotacao informação sobre a duração da votação.
	 */
	public void execute(DuracaoVotacaoDTO duracaoVotacao) {
		this.duracaoVotacao = duracaoVotacao;
		if (duracaoVotacao.getTempoDuracao() != null) {
			definirSchedule(duracaoVotacao.getTempoDuracao());
		} else {
			definirSchedule(TEMPO_PADRAO_DURACAO);
		}
	}

	/**
	 * Método para executar o agendador para encerramento da votação
	 *
	 * @param tempoDuracaoVotacao o tempo de espera até a execução do agendador.
	 */
	private void definirSchedule(Long tempoDuracaoVotacao) {
		scheduler.schedule(task, tempoDuracaoVotacao, TimeUnit.MINUTES);
	}

}
