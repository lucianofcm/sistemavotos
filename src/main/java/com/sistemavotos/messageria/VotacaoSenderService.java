/*
 * 
 */
package com.sistemavotos.messageria;

import java.io.Serializable;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sistemavotos.dto.ResultadoVotacaoDTO;
import com.sistemavotos.rules.VotacaoRule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VotacaoSenderService implements Serializable{
	

	private static final long serialVersionUID = 2386733146452938286L;
	
	@Autowired
	private transient RabbitTemplate rabbitTemplate;

	@Autowired
	public VotacaoSenderService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	
	
	/**
	 * Send pauta encerrada.
	 *
	 * @param resultadoVotacao resultado votacao
	 * @throws AmqpException amqp exception
	 */
	public void sendPautaEncerrada(ResultadoVotacaoDTO resultadoVotacao) throws AmqpException {
		log.info("Enviando resultado votação.");
		rabbitTemplate.convertAndSend(exchange, resultadoVotacao);
	}
}