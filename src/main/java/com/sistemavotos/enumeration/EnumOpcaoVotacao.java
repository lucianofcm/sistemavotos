package com.sistemavotos.enumeration;

public enum EnumOpcaoVotacao {
	S(0), N(1);

	private final Integer cod;

	public Integer getCod() {
		return cod;
	}

	EnumOpcaoVotacao(Integer cod) {
		this.cod = cod;
	}
}
