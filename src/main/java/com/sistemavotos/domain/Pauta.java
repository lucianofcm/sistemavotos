package com.sistemavotos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sistemavotos.enumeration.EnumAtiva;

import lombok.Data;

@Entity
@Table(name = "pauta")
@Data
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String titulo;

	@Column
	private String descricao;
	
	@Column(name = "ind_ativa")
	@Enumerated(EnumType.STRING)
	private EnumAtiva indAtiva;
	
	

}
