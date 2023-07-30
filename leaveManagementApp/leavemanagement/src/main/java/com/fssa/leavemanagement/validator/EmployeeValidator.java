package com.fssa.leavemanagement.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import com.fssa.leavemanagement.exceptions.InvalidEmployeeException;
import com.fssa.leavemanagement.model.Employee;
import com.fssa.leavemanagement.model.EmployeeErrors;

public class EmployeeValidator {
	
	public static boolean validateEmployee(Employee employee) throws InvalidEmployeeException {
		if (employee == null) {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_EMPLOYEE);
		}
		try {
			validateName(employee.getName());
			validateEmail(employee.getEmail());
			validatePassword(employee.getPassword());
			validateDate(employee.getDateOfJoin());
		}catch (InvalidEmployeeException e) {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_EMPLOYEE);
		}
		
		
		return true;
	}

	public static boolean validateName(String name) throws InvalidEmployeeException {
		if (name == null || name.length() < 3 || name.trim().length() <= 0) {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_NAME);

		}
		return true;
	}
	

	public static boolean validateId(int id) throws InvalidEmployeeException {
		if(id <=0) {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_ID);
		}
		return true;
	}
	
	public static boolean validateEmail(String email) throws InvalidEmployeeException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		boolean matches = Pattern.compile(regex).matcher(email).matches();
		if (matches) {
			return true;
		} else {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_EMAIL);
		}
	}

	public static boolean validatePassword(String password) throws InvalidEmployeeException {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		boolean matches = Pattern.compile(regex).matcher(password).matches();
		if (matches) {
			return true;
		} else {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_PASSWORD);
		}
	}

	public static boolean validateDate(LocalDate date) throws InvalidEmployeeException {
		if (date == null || date.isAfter(LocalDate.now())) {
			throw new InvalidEmployeeException(EmployeeErrors.INVALID_DATE);
		}
		return true;
	}

}
