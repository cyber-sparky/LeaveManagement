package com.fssa.leavemanagement.service;



import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.leavemanagement.exceptions.InvalidEmployeeException;
import com.fssa.leavemanagement.model.Employee;
import com.fssa.leavemanagement.model.EmployeeErrors;

public class TestEmployeeService {

	@Test
	void testAddEmployee() throws InvalidEmployeeException {
		Employee emp = new Employee("pranaw", "pranaw1@gmail.com", "IPranaw@123%", LocalDate.of(2021, 10, 10), true, null, null);
		Assertions.assertTrue(EmployeeService.addEmployee(emp));
	}
	
	@Test
	void testInvalidAddEmployee()  {

		try {
			Assertions.assertTrue(EmployeeService.addEmployee(null));
			Assertions.fail("Invalid Add Employee failed");
		} catch (InvalidEmployeeException e) {
			Assertions.assertEquals(EmployeeErrors.INVALID_EMPLOYEE, e.getMessage());
		}
	}
	@Test
	void testUpdateEmployee() {
		
	}
}
