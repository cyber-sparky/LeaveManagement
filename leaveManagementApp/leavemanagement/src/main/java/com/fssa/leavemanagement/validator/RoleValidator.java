package com.fssa.leavemanagement.validator;

import java.util.regex.Pattern;

import com.fssa.leavemanagement.errors.RoleErrors;
import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;

public class RoleValidator {
	public static boolean validate(Role role) throws InvalidRoleException {
		if (role == null) {
			throw new InvalidRoleException(RoleErrors.INVALID_ROLE);
		}
		validateId(role.getId());
		validateName(role.getName());
		return true;
	}

	public static boolean validateId(int id) throws InvalidRoleException {
		if (id <= 0) {
			throw new InvalidRoleException(RoleErrors.INVALID_ID);
		}
		return true;
	}

	public static boolean validateName(String name) throws InvalidRoleException {
		if (name == null || name.trim().length() <= 2) {
			throw new InvalidRoleException(RoleErrors.INVALID_NAME);
		}
		String regex = "^[A-Za-z ]{2,}$";
		boolean match = Pattern.compile(regex).matcher(name).matches();
		if (match) {
			return true;
		} else {
			throw new InvalidRoleException(RoleErrors.INVALID_NAME);
		}
	}
}
