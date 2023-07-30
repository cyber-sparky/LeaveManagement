package com.fssa.leavemanagement.dao;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;
import com.fssa.leavemanagement.model.RoleErrors;
import com.fssa.leavemanagement.validator.RoleValidator;

public class TestRoleDao {

	@Test
	void testAddRole() throws InvalidRoleException, SQLException {
		Role role = new Role(1, "IT");
		Assertions.assertTrue(RoleDao.addRole(role));

	}

	@Test
	void testInvalidAddRole() throws SQLException {
		try {
			Assertions.assertTrue(RoleDao.addRole(null));
			// Should throw an exception for a null role

		} catch (InvalidRoleException e) {
			Assertions.assertEquals("Invalid Role passed to DAO Layer", e.getMessage());
		}
	}
	
	@Test
	void testUpdateRole() throws InvalidRoleException {
		Role role = new Role(1,"IT");
		Assertions.assertTrue(RoleDao.updateRole(role, 2));
	}
	@Test
	void testInvalidUpdateRole() {
		try {
			Assertions.assertTrue(RoleDao.updateRole(null, 2));
		} catch (InvalidRoleException e) {
			Assertions.assertEquals("Invalid Role passed to DAO Layer", e.getMessage());
		}
	}
	@Test
	void testReadRole() throws SQLException {
		try {
			Assertions.assertTrue(RoleDao.readRole());
		} catch (InvalidRoleException e) {
			Assertions.assertEquals("Invalid Role passed to DAO Layer", e.getMessage());
		}
	}
	@Test
	void testDeleteRole() throws SQLException {
		try {
			int id = 2;
			Assertions.assertTrue(RoleDao.deleteRole(id));
		} catch (InvalidRoleException e) {
			Assertions.assertEquals("Invalid Role passed to DAO Layer", e.getMessage());
		}
	}
	@Test
	void testInvalidDeleteRole() throws SQLException {
		try {
			int id = -1;
			Assertions.assertTrue(RoleDao.deleteRole(id));
		} catch (InvalidRoleException e) {
			Assertions.assertEquals(RoleErrors.INVALID_ID, e.getMessage());
		}
	}
	@Test
	void testFindRoleByName() throws InvalidRoleException, SQLException {
		String find = "IT";
		Assertions.assertTrue(RoleDao.findRoleByName(find));
	}
	@Test
	void testInvalidFindRoleByName() {
		String find = "IT";
		try {
			Assertions.assertTrue(RoleDao.findRoleByName(find));
		} catch (InvalidRoleException | SQLException e) {
			Assertions.assertEquals("Cannot find role", e.getMessage());
		}
	}
}
