package com.fssa.leavemanagement.errors;

public interface RoleErrors {
	public static final String INVALID_ID = "Id cannot be 0 or lesser than 0";
	public static final String INVALID_NAME = "Role name cannot be lesser than 2 characters";
	public static final String INVALID_ROLE = "Role cannot be null";
}
