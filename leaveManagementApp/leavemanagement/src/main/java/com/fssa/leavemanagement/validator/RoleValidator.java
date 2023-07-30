package com.fssa.leavemanagement.validator;

import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;
import com.fssa.leavemanagement.model.RoleErrors;

public class RoleValidator {
	public static boolean validate(Role role) throws InvalidRoleException {
		if(role == null) {
			throw new InvalidRoleException(RoleErrors.INVALID_ROLE);
		}
		validateId(role.getId());
		validateName(role.getName());
		return true;
	}

	public static boolean validateId(int id) throws InvalidRoleException {
		if (id <=0) {
			throw new InvalidRoleException(RoleErrors.INVALID_ID);
		}
		return true;
	}

	public static boolean validateName(String name) throws InvalidRoleException {
		if (name.trim().length() <= 0 || name.length() < 2) {
			throw new InvalidRoleException(RoleErrors.INVALID_NAME);
		}
		return true;
	}
}
