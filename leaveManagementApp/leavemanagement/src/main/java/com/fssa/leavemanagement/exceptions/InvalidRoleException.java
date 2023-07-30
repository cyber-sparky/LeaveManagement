package com.fssa.leavemanagement.exceptions;

public class InvalidRoleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRoleException(String msg) {
		super(msg);
	}

	public InvalidRoleException(Throwable te) {
		super(te);
	}

	public InvalidRoleException(String msg, Throwable te) {
		super(msg, te);
	}
}