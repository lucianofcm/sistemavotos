package com.sistemavotos.dto;

import com.sistemavotos.enumeration.EnumOpcaoVotacao;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class VotacaoDTO {

	private String cpfUsuario;
	private PautaDTO pauta;
	private EnumOpcaoVotacao opcaoVoto;

}
