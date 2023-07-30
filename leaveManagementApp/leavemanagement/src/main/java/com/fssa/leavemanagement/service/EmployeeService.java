package com.fssa.leavemanagement.service;

import java.sql.SQLException;

import com.fssa.leavemanagement.dao.EmployeeDao;
import com.fssa.leavemanagement.exceptions.DAOException;
import com.fssa.leavemanagement.exceptions.InvalidEmployeeException;
import com.fssa.leavemanagement.model.Employee;
import com.fssa.leavemanagement.validator.EmployeeValidator;

public class EmployeeService {

	public static boolean addEmployee(Employee employee) throws InvalidEmployeeException {
		if (EmployeeValidator.validateEmployee(employee)) {
			EmployeeDao.addEmployee(employee);
		}
		return true;
	}

	public static boolean readEmployee() throws InvalidEmployeeException, DAOException, SQLException {
		EmployeeDao.readEmployee();
		return true;

	}

	public static boolean updateEmployee(Employee employee, int id) throws InvalidEmployeeException, DAOException {
		if (EmployeeValidator.validateEmployee(employee)) {
			EmployeeDao.updateEmployee(employee, id);
		}
		return true;

	}

	public static boolean deleteEmployee(int id) throws InvalidEmployeeException, DAOException {
		if (EmployeeValidator.validateId(id)) {
			EmployeeDao.deleteEmployee(id);
		}
		return true;

	}

	public static boolean findEmployeeByName(String name) throws InvalidEmployeeException, DAOException, SQLException {
		if (EmployeeValidator.validateName(name)) {
			EmployeeDao.findEmployeeByName(name);
		}
		return true;

	}
}
