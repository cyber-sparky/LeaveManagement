package com.fssa.leavemanagement.service;

import java.sql.SQLException;

import com.fssa.leavemanagement.dao.EmployeeRoleDetailsDao;
import com.fssa.leavemanagement.errors.EmployeeErrors;
import com.fssa.leavemanagement.exceptions.DAOException;
import com.fssa.leavemanagement.model.EmployeeRoleDetails;
import com.fssa.leavemanagement.util.Logger;

public class EmployeeRoleDetailsService {
	static Logger logger = new Logger();
	public static boolean addEmployeeRoleDetails(EmployeeRoleDetails erd) throws DAOException {
		try {
			return EmployeeRoleDetailsDao.addEmployeeRoleDetails(erd);
		} catch (SQLException e) {
			throw new DAOException(EmployeeErrors.INVALID_EMPLOYEE_ROLE_DETAIL);
		}
	}

	public static boolean updateEmployeeRoleDetails(EmployeeRoleDetails erd) throws DAOException {
		try {
			return EmployeeRoleDetailsDao.updateEmployeeRoleDetails(erd);
		} catch (SQLException e) {
			throw new DAOException(EmployeeErrors.INVALID_EMPLOYEE_ROLE_DETAIL);
			
		}
	}
	public static boolean getAllEmployeeRoleDetails() {
		try {
			return EmployeeRoleDetailsDao.getAllEmployeeRoleDetails();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
}