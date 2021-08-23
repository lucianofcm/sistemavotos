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
	
	private LocalDateTime fimVotacao;
	
	private Long tempoDuracao;
	
	private Pauta pauta;

}
