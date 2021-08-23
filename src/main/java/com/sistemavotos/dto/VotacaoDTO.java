package com.sistemavotos.dto;

import java.io.Serializable;

import com.sistemavotos.enumeration.EnumOpcaoVotacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class VotacaoDTO implements Serializable {

	private static final long serialVersionUID = 5383537332282060707L;

	private String cpfUsuario;
	private PautaDTO pauta;
	private EnumOpcaoVotacao opcaoVoto;

}
