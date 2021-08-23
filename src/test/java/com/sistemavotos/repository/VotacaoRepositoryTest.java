package com.sistemavotos.repository;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
 class VotacaoRepositoryTest {
	 
	 @Autowired
	 private VotacaoRepository votacaoRepository;

	@Test
	void testLocalizarPorCpfEPauta() throws Exception {
		assertThat(votacaoRepository.localizarPorCpfEPauta("7778848691", 1), not(nullValue()));
	}

	@Test
	void testListarVotacaoPorPauta() throws Exception {
		assertThat(votacaoRepository.listarVotacaoPorPauta(1), not(Collections.emptyList()));
	}

}
