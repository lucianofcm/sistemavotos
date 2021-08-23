package com.sistemavotos.service;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.enumeration.EnumOpcaoVotacao;
import com.sistemavotos.messageria.VotacaoSenderService;

@SpringBootTest
@AutoConfigureMockMvc
@SpringRabbitTest
@ActiveProfiles("tes")
class RabbitSenderTest {

	@Autowired
	private VotacaoSenderService rabbitMqSender;

	@Test
	void testExecute() throws Exception {
		rabbitMqSender.sendPautaEncerrada(VotacaoDTO.builder().cpfUsuario("77788486191").opcaoVoto(EnumOpcaoVotacao.S).build());	
	}

}
