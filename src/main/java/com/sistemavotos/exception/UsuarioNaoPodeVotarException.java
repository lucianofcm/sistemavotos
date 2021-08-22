package com.sistemavotos.exception;
public class UsuarioNaoPodeVotarException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoPodeVotarException(String message){
        super(message);
    }


}