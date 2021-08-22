package com.sistemavotos.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sistemavotos.exception.UsuarioNaoPodeVotarException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
class ValidacaoCpfServiceTest {
	
	@Autowired
	private ValidacaoCpfService validacaoCpfService;

	/*
	 * @Test void testUsuarioPodeVotar() throws Exception {
	 * assertThat(validacaoCpfService.usuarioPodeVotar("51035160552").getStatus(),
	 * equalTo("ABLE_TO_VOTE")); }
	 */
	@Test
	void testUsuarioNaoPodeVotar() throws Exception {
		assertThrows(UsuarioNaoPodeVotarException.class,() -> validacaoCpfService.usuarioPodeVotar("77788899900"));
	}

}
