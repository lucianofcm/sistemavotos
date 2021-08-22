package com.sistemavotos.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.sistemavotos.dto.StatusVotoDTO;
import com.sistemavotos.exception.UsuarioNaoPodeVotarException;

@Repository
public class ValidacaoCpfRepository {

	@Value("${spring.urlValidadorCPF}")
	private String urlIntegraIdea;

	@Autowired
	private RestTemplate restTemplate;
	
	

	/**
	 * Serviço que fornece se o usuário pode votar ou não
	 *
	 * @param numCpf numero de cpf válido
	 * @return String retornar (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE)
	 * @throws Exception caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found)
	 */
	public ResponseEntity<StatusVotoDTO> validarCPF(String numCpf) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("numCpf", numCpf);
			return restTemplate.exchange(urlIntegraIdea.concat("/{numCpf}"), HttpMethod.GET,
					new HttpEntity<>(new HttpHeaders()), StatusVotoDTO.class, params);
			
		} catch (HttpStatusCodeException e) {
			throw new UsuarioNaoPodeVotarException("Usuario não pode votar.");
		}
	}

}
