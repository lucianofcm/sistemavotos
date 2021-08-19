package com.sistemavotos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sistemavotos.enumeration.EnumOpcaoVotacao;

import lombok.Data;

@Entity
@Table(name = "votacao", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "pauta_id" }) })
@Data
public class Votacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
    @JoinColumn(name = "pauta_id")
	private Pauta pauta;
	
	@Column(name="opcao_votacao")
	@Enumerated(EnumType.STRING)
	private EnumOpcaoVotacao opcaoVoto;
	

}
