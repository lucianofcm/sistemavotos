package com.sistemavotos.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
class AgendadorServiceTest {
	
	@Autowired
	private AgendadorService agendadorService;

	@Test
	void testExecute() throws Exception {
		agendadorService.execute(5);
	}

}
