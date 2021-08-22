package com.sistemavotos.exception;
public class UsuarioJaVotouException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioJaVotouException(String message){
        super(message);
    }


}