package com.sistemavotos.dto;

import com.sistemavotos.enumeration.EnumAtiva;

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
public class PautaDTO {
	private Integer id;

	private String titulo;	

	private String descricao;

	private EnumAtiva indAtiva;
}
