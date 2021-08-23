package com.sistemavotos.messageria;

import java.io.Serializable;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.dto.PautaDTO;
import com.sistemavotos.dto.ResultadoVotacaoDTO;

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
	
	
	public void sendPautaEncerrada(ResultadoVotacaoDTO resultadoVotacao) throws AmqpException {
		rabbitTemplate.convertAndSend(exchange, resultadoVotacao);
	}
}