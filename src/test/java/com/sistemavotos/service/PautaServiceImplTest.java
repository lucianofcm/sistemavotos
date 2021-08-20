package com.sistemavotos.service;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.enumeration.EnumAtiva;
import com.sistemavotos.exception.PautaException;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
class PautaServiceImplTest {

	@Autowired
	private PautaService pautaService;

	@Test
	void testCadastrarPauta() {
		assertThat(pautaService.cadastrarPauta(Pauta.builder().indAtiva(EnumAtiva.S)
				.descricao("Votação para definir o indefinível").titulo("Pauta 1").build()), not(nullValue()));
	}

	@Test
	void testLocalizarPautaPorID() throws Exception {
		assertThat(pautaService.localizarPautaPorID(1), not(nullValue()));
	}
	
	@Test
	void testBuscarPorPautaInexistente() throws Exception {
		assertThrows(PautaException.class,() -> pautaService.localizarPautaPorID(100));
	}
}
