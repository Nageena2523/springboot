package com.tejait.batch15.exceptions;

public class MobileAlreadyexists  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MobileAlreadyexists () {
		super();
	}
	public MobileAlreadyexists (String msg) {
		super(msg);
	}

}
