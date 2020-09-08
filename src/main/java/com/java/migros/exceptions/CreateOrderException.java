package com.java.migros.exceptions;

public class CreateOrderException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CreateOrderException(String message) {
        super(message);
    }
	
}
