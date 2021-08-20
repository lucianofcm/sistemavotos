package com.sistemavotos.exception;
public class VotacaoEncerradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VotacaoEncerradaException(String message){
        super(message);
    }


}