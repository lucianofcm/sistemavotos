package com.sistemavotos.respository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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
class PautaRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Test
	void testFindAll() throws Exception {
		assertThat(usuarioRepo.findAll().isEmpty(), equalTo(false));
	}
	
	@Test
	void testFindByOne() throws Exception {
		assertThat(usuarioRepo.getById(1), not(nullValue()));
	}
	
	@Test
	void testSave() throws Exception {
		usuarioRepo.save(Usuario.builder().nome("Adriana").cpf("77788386191").build());
		assertThat(usuarioRepo.findByCpf("77788386191"),not(nullValue()));
	}
}
