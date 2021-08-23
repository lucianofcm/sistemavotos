package com.sistemavotos.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.messageria.VotacaoSenderService;

@Service
public class AgendadorService {

	private static final long TEMPO_PADRAO_DURACAO = 2l;

	@Autowired
	private VotacaoSenderService votacaoSenderService;

	private DuracaoVotacaoDTO duracaoVotacao;

	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

	Runnable task = () -> votacaoSenderService.sendPautaEncerrada(duracaoVotacao);

	public void execute(DuracaoVotacaoDTO duracaoVotacao) {
		this.duracaoVotacao = duracaoVotacao;
		if (duracaoVotacao.getTempoDuracao() != null) {
			definirSchedule(duracaoVotacao.getTempoDuracao());
		} else {
			definirSchedule(TEMPO_PADRAO_DURACAO);
		}
	}

	private void definirSchedule(Long tempoDuracaoVotacao) {
		scheduler.schedule(task, tempoDuracaoVotacao, TimeUnit.MINUTES);
	}

}
