package com.tejait.batch15.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidAmountException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAmountException( String msg) {
		super(msg);
	}
	
	

}
