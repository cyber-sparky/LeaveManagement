package com.fssa.leavemanagement.exceptions;

public class InvalidEmployeeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmployeeException(String msg) {
		super(msg);
	}

	public InvalidEmployeeException(Throwable te) {
		super(te);
	}

	public InvalidEmployeeException(String msg, Throwable te) {
		super(msg, te);
	}
}
