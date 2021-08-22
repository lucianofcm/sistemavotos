package com.sistemavotos.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.DuracaoVotacaoDTO;
import com.sistemavotos.dto.PautaDTO;
import com.sistemavotos.dto.VotacaoDTO;
import com.sistemavotos.enumeration.EnumOpcaoVotacao;
import com.sistemavotos.exception.BasicException;
import com.sistemavotos.exception.VotacaoEncerradaException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
class VotacaoServiceImplTest {

	@Autowired
	private PautaService pautaService;
	@Autowired
	private VotacaoService votacaoService;

	@Test
	void testVotacaoJaIniciada() {
		Pauta pauta = pautaService.localizarPautaPorID(1);
		assertThrows(BasicException.class, () -> votacaoService
				.iniciarVotacao(DuracaoVotacaoDTO.builder().pauta(pauta).tempoDuracao(10l).build()));
	}

	@Test
	void testVotacaoJaEncerrada() {
		assertThrows(VotacaoEncerradaException.class,
				() -> votacaoService.votar(VotacaoDTO.builder().pauta(PautaDTO.builder().id(1).build())
						.cpfUsuario("77788486191").opcaoVoto(EnumOpcaoVotacao.S).build()));
	}
	
	/*
	 * @Test void testVotarComSucesso() throws Exception {
	 * assertThat(votacaoService.votar(VotacaoDTO.builder().pauta(PautaDTO.builder()
	 * .id(2).build())
	 * .cpfUsuario("77788486191").opcaoVoto(EnumOpcaoVotacao.S).build()),
	 * equalTo("Votação realizada com sucesso!")); }
	 */
}
