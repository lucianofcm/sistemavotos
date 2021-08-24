package com.sistemavotos.dto;

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
public class ResultadoVotacaoDTO {	
	
	private PautaDTO pauta;
	private DuracaoVotacaoDTO duracaoDTO;
	private Long qtdeVotosSim;
	private Long qtdeVotosNao;

}
