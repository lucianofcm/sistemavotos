package com.sistemavotos.enumeration;

public enum EnumStatusVoto {
	
	ABLE_TO_VOTE(0), UNABLE_TO_VOTE(1);

	private final Integer cod;

	public Integer getCod() {
		return cod;
	}

	EnumStatusVoto(Integer cod) {
		this.cod = cod;
	}

}
