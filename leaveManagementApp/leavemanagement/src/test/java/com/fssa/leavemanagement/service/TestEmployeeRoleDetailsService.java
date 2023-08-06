package com.fssa.leavemanagement.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.leavemanagement.errors.EmployeeErrors;
import com.fssa.leavemanagement.exceptions.DAOException;
import com.fssa.leavemanagement.model.EmployeeRoleDetails;

public class TestEmployeeRoleDetailsService {
@Test
void testAddEmployeeRoleDetails() throws DAOException {
	EmployeeRoleDetails erd = new EmployeeRoleDetails();
	erd.setEmployeeId(5);
	erd.setReportingManagerId(3);
	erd.setRoleId(5);
	Assertions.assertTrue(EmployeeRoleDetailsService.addEmployeeRoleDetails(erd));
}
@Test
void testupdateEmployeeRoleDetails() throws DAOException {
	EmployeeRoleDetails erd = new EmployeeRoleDetails();
	erd.setEmployeeId(5);
	erd.setReportingManagerId(3);
	erd.setRoleId(5);
	Assertions.assertTrue(EmployeeRoleDetailsService.updateEmployeeRoleDetails(erd));
}
@Test 
void testGetAllEmployeeRoleDetails(){
	Assertions.assertTrue(EmployeeRoleDetailsService.getAllEmployeeRoleDetails());
}
@Test 
void testInvalidAddEmployeeRoleDetails() throws DAOException {
	EmployeeRoleDetails erd = new EmployeeRoleDetails();
	Assertions.assertThrows(DAOException.class,()->{
		EmployeeRoleDetailsService.addEmployeeRoleDetails(erd);
	});
}
@Test 
void testInvalidUpdateEmployeeRoleDetails()  {
	EmployeeRoleDetails erd = new EmployeeRoleDetails();
	 try {
		EmployeeRoleDetailsService.addEmployeeRoleDetails(erd);
	} catch (DAOException e) {
		Assertions.assertEquals(EmployeeErrors.INVALID_EMPLOYEE_ROLE_DETAIL, e.getMessage());
	}
}
}