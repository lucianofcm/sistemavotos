package com.sistemavotos.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
class PautaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void validarServicoPautaTest() throws Exception {

		mvc.perform(get("/pauta/").headers(new HttpHeaders()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	void listarPautas() throws Exception {
		mvc.perform(get("/pauta/").headers(new HttpHeaders()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].titulo", equalTo("Pauta 1")))
				.andExpect(jsonPath("$[1].titulo", equalTo("Pauta 2")));

	}

	@Test
	void testLocalizarPautaPorId() throws Exception {
		mvc.perform(get("/pauta/1").headers(new HttpHeaders()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("titulo", equalTo("Pauta 1")));
	}

}
