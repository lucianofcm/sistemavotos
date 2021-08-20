package com.sistemavotos.dto;

import com.sistemavotos.enumeration.EnumAtiva;

import lombok.Data;

@Data
public class PautaDTO {
	private Integer id;

	private String titulo;	

	private String descricao;

	private EnumAtiva indAtiva;
}
