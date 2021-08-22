package com.sistemavotos.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import com.sistemavotos.enumeration.EnumAtiva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "duracao_votacao",uniqueConstraints = { @UniqueConstraint(columnNames = { "pauta_id" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class DuracaoVotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "inicio_votacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime inicioVotacao;
	
	@Column(name = "fim_votacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime fimVotacao;
	
	@Column(name = "duracao_votacao")
	private Long tempoDuracao;
	
	@OneToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;
	

}
