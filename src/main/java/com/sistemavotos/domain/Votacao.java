package com.sistemavotos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sistemavotos.enumeration.EnumOpcaoVotacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "votacao", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "pauta_id" }) })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Votacao implements Serializable{

	private static final long serialVersionUID = 117306363911481810L;

	@Id
	private String cpfUsuario;

	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
    @JoinColumn(name = "pauta_id")
	private Pauta pauta;
	
	@Column(name="opcao_voto")
	@Enumerated(EnumType.STRING)
	private EnumOpcaoVotacao opcaoVoto;
	

}
