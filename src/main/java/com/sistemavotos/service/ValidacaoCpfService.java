package com.sistemavotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistemavotos.domain.Pauta;
import com.sistemavotos.dto.StatusVotoDTO;
import com.sistemavotos.enumeration.EnumAtiva;
import com.sistemavotos.enumeration.EnumStatusVoto;
import com.sistemavotos.exception.PautaException;
import com.sistemavotos.exception.UsuarioNaoPodeVotarException;
import com.sistemavotos.repository.PautaRepository;
import com.sistemavotos.repository.ValidacaoCpfRepository;

/**
 * Classe ValidacaoCpfService.
 */
@Service
public class ValidacaoCpfService {

	@Autowired
	private ValidacaoCpfRepository validacaoCpfRepository;

	/**
	 * Método que verifica se a partir de um cpf o usuário pode ou não votar
	 *
	 * @param numCPF número do cpf do usuário
	 * @return StatusVotoDTO caso o usário possua permissão para votar retorna seu status
	 * @throws UsuarioNaoPodeVotarException caso o usuário não tenha permissáo para votar retonar uma exceção.
	 */
	public StatusVotoDTO usuarioPodeVotar(String numCPF) throws UsuarioNaoPodeVotarException {

		StatusVotoDTO status = validacaoCpfRepository.validarCPF(numCPF).getBody();
		if (status != null && EnumStatusVoto.UNABLE_TO_VOTE.name().equals(status.getStatus())) {
			throw new UsuarioNaoPodeVotarException("O usuário não pode votar");
		}
		return status;
	}

}
