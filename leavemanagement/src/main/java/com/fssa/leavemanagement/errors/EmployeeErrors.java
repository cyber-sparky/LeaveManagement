package com.fssa.leavemanagement.errors;

public interface EmployeeErrors {
	public static final String INVALID_NAME = "Invalid Employee Name cannot be empty or lesser than 3 characters";
	public static final String INVALID_EMAIL = "Email should contains @ and shouldn't have spaces";
	public static final String INVALID_PASSWORD = "Minimum eight characters, at least one letter, one number and one special character";
	public static final String INVALID_DATE = "Date of Joining & Relieving cannot be in the future date";
	public static final String INVALID_STATUS = "Invalid Employee Status";
	public static final String INVALID_EMPLOYEE = "Invalid Employee";
	public static final String CANNOT_ADD_EMPLOYEE = "Cannot add Employee";
	public static final String INVALID_ID = "Id cannot 0 or negative or more than 2147483647";
	public static final String INVALID_EMPLOYEE_ROLE_DETAIL = "Invalid Employee Role Detail passed in DAO Layer";
	
}
