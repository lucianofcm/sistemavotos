package com.sistemavotos.enumeration;

public enum EnumAtiva {
	S(0), N(1);

	private final Integer cod;

	public Integer getCod() {
		return cod;
	}

	EnumAtiva(Integer cod) {
		this.cod = cod;
	}
}
