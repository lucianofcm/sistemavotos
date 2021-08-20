package com.sistemavotos.respository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sistemavotos.domain.Usuario;
import com.sistemavotos.repository.UsuarioRepository;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tes")
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Test
	void testFindAll() {
		assertThat(usuarioRepo.findAll().isEmpty(), equalTo(false));
	}
	
	@Test
	void testFindByOne() {
		assertThat(usuarioRepo.getById(1), not(nullValue()));
	}
	
	@Test
	void testSave() {
		String cpf = RandomStringUtils.randomNumeric(11);
		usuarioRepo.save(Usuario.builder().nome("Adriana").cpf(cpf).build());
		assertThat(usuarioRepo.findByCpf(cpf),not(nullValue()));
	}
}
