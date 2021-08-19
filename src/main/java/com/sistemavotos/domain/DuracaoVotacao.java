package com.sistemavotos.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "duracao_votacao")
@Data
public class DuracaoVotacao {

	@Id
	private Integer id;

	@Column(name = "inicio_votacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime inicioVotacao;
	
	@Column(name = "fim_votacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime fimVotacao;


	@ManyToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;
	

}
