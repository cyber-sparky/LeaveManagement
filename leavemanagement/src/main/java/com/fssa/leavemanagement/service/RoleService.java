package com.fssa.leavemanagement.service;

import java.sql.SQLException;

import com.fssa.leavemanagement.dao.RoleDao;
import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;
import com.fssa.leavemanagement.validator.RoleValidator;

public class RoleService {

	public static boolean addRole(Role role) throws InvalidRoleException, SQLException {
		if (RoleValidator.validate(role)) {
			RoleDao.addRole(role);
		}
		return true;

	}

	public static boolean readRole() throws InvalidRoleException, SQLException {
		RoleDao.readRole();
		return true;
	}

	public static boolean deleteRole(Role role) throws InvalidRoleException, SQLException {
		if (RoleValidator.validate(role)) {
			RoleDao.deleteRole(role);
		}
		return true;

	}

	public static boolean findRoleByName(String name) throws InvalidRoleException, SQLException {
		if (RoleValidator.validateName(name)) {
			RoleDao.findRoleByName(name);
		}
		return true;

	}
}
