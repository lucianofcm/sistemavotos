package com.sistemavotos.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sistemavotos.domain.DuracaoVotacao;
import com.sistemavotos.domain.Pauta;
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
public class DuracaoVotacaoDTO {

	private Integer id;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime inicioVotacao;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fimVotacao;
	private Long tempoDuracao;
	private PautaDTO pauta;

	public DuracaoVotacaoDTO(DuracaoVotacao duracaoVotacao) {
		this.id = duracaoVotacao.getId();
		this.inicioVotacao = duracaoVotacao.getInicioVotacao();
		this.fimVotacao = duracaoVotacao.getFimVotacao();
		this.pauta = new PautaDTO(duracaoVotacao.getPauta());
		this.tempoDuracao = duracaoVotacao.getTempoDuracao();
	}

}
