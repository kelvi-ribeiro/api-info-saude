package com.info.saude.services.exceptions;

public class LinkSenhaEsquecidaUsado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LinkSenhaEsquecidaUsado(String msg) {
		super(msg);
	}
	
	public LinkSenhaEsquecidaUsado(String msg, Throwable cause) {
		super(msg, cause);
	}

}
