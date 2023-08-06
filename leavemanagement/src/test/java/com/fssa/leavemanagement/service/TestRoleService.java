package com.fssa.leavemanagement.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.leavemanagement.errors.RoleErrors;
import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;

public class TestRoleService {
	@Test
	void testAddRole() throws InvalidRoleException, SQLException {
		Role role = new Role(1, "Junior Designer");
		Assertions.assertTrue(RoleService.addRole(role));
	}

	@Test
	void testInvalidAddRole() {
		Role role = new Role();

		try {
			Assertions.assertTrue(RoleService.addRole(role));
		} catch (InvalidRoleException | SQLException e) {
			Assertions.assertEquals(RoleErrors.INVALID_ID, e.getMessage());
		}

	}

	@Test
	void testDeleteRole() throws InvalidRoleException, SQLException {
		Role role = new Role(1, "Junior Designer");
		Assertions.assertTrue(RoleService.deleteRole(role));
	}

	@Test
	void testInvalidDeleteRole() {
		Role role = new Role(1, "abc");
		try {
			Assertions.assertTrue(RoleService.deleteRole(role));
		} catch (InvalidRoleException | SQLException e) {
			Assertions.assertEquals(RoleErrors.INVALID_NAME, e.getMessage());
		}
	}

	@Test
	void testReadRole() throws InvalidRoleException, SQLException {
		Assertions.assertTrue(RoleService.readRole());
	}

	@Test
	void testFindRoleByName() throws InvalidRoleException, SQLException {
		Role role = new Role(1, "CEO");
		Assertions.assertTrue(RoleService.findRoleByName(role.getName()));
	}
}
