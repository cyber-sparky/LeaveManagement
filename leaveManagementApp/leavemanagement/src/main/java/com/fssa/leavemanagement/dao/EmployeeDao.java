package com.fssa.leavemanagement.dao;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.leavemanagement.errors.EmployeeErrors;
import com.fssa.leavemanagement.exceptions.DAOException;
import com.fssa.leavemanagement.exceptions.InvalidEmployeeException;
import com.fssa.leavemanagement.model.Employee;
import com.fssa.leavemanagement.util.ConnectionUtil;
import com.fssa.leavemanagement.util.Logger;
import com.fssa.leavemanagement.validator.EmployeeValidator;

public class EmployeeDao {

	static Logger logger = new Logger();

	public static int getEmployeeIdByName(String name) throws SQLException {
		int id = 0;
		String query = "SELECT id FROM employee WHERE name = ?";
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, name);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						id = rs.getInt(1);
					}
				}
			}
		}
		return id;
	}

	public static boolean addEmployee(Employee employee, String role) throws InvalidEmployeeException {
		int employeeRoleDetailsRows = 0;
		try {
			EmployeeValidator.validateEmployee(employee);
		} catch (InvalidEmployeeException e) {
			e.printStackTrace();
			throw new InvalidEmployeeException("Invalid employee passed to DAO Layer", e);
		}

		if (employee.isStatus()) {

			try (Connection connection = ConnectionUtil.getConnection()) {

				String query = "INSERT INTO employee(name,email, is_active,password) VALUES (?,?,?,?);";

				try (PreparedStatement pst = connection.prepareStatement(query)) {
					pst.setString(1, employee.getName());
					pst.setString(2, employee.getEmail());
					pst.setBoolean(3, employee.isStatus());
					pst.setString(4, employee.getPassword());

					int rows = pst.executeUpdate();
					int roleId = RoleDao.getRoleIdByName(role);
					int employeeId = EmployeeDao.getEmployeeIdByName(employee.getName());
					if (role.equals("CEO")) {
						String queryEmployeeDetail = "INSERT INTO employee_role_details (employee_id,role_id) VALUES (?,?);";
						try (PreparedStatement pst1 = connection.prepareStatement(queryEmployeeDetail)) {
							pst1.setInt(1, employeeId);
							pst1.setInt(2, roleId);

							employeeRoleDetailsRows = pst1.executeUpdate();
						}
					} else {
						int managerId = EmployeeDao.getEmployeeIdByName(employee.getManager().getName());
						String queryEmployeeDetail = "INSERT INTO employee_role_details (employee_id,role_id,reporting_manager_id) VALUES (?,?,?);";
						try (PreparedStatement pst1 = connection.prepareStatement(queryEmployeeDetail)) {
							pst1.setInt(1, employeeId);
							pst1.setInt(2, roleId);
							pst1.setInt(3, managerId);

							employeeRoleDetailsRows = pst1.executeUpdate();
						}
					}

					return (rows > 0 && employeeRoleDetailsRows > 0) ? true : false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new InvalidEmployeeException(EmployeeErrors.CANNOT_ADD_EMPLOYEE);
			}
		} else {

			try (Connection connection = ConnectionUtil.getConnection()) {

				String query = "INSERT INTO employee(name,email, is_active,password,date_of_relieving) VALUES (?,?,?,?,?);";
				try (PreparedStatement pst = connection.prepareStatement(query)) {
					pst.setString(1, employee.getName());
					pst.setString(2, employee.getEmail());
					pst.setBoolean(3, employee.isStatus());
					pst.setString(4, employee.getPassword());
					pst.setDate(5, java.sql.Date.valueOf(employee.getDateOfRelieve()));
					int rows = pst.executeUpdate();
					return (rows > 0) ? true : false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new InvalidEmployeeException(EmployeeErrors.CANNOT_ADD_EMPLOYEE);
			}
		}

	}


	public static String getRoleByEmployeeName(String name) throws SQLException {
		String role = null;
		String query = "SELECT e.name AS employee_name, r.name AS role_name\r\n"
				+ "FROM employee AS e\r\n"
				+ "LEFT JOIN employee_role_details AS erd ON e.id = erd.employee_id\r\n"
				+ "LEFT JOIN role AS r ON erd.role_id = r.id\r\n"
				+ "WHERE e.name = ?;";
		try(Connection connection = ConnectionUtil.getConnection()){
			try(PreparedStatement pst = connection.prepareStatement(query)){
				pst.setString(1, name);
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						role = rs.getString("role_name");
					}
				}
			}
		}
		return role;
	}
	public static boolean updateEmployee(Employee employee, int id) throws DAOException {
	    try {
	        EmployeeValidator.validateEmployee(employee);
	    } catch (InvalidEmployeeException e) {
	        e.printStackTrace();
	        throw new DAOException("Invalid employee passed to DAO Layer", e);
	    }

	    try (Connection connection = ConnectionUtil.getConnection()) {
	    	if (employee.isStatus()) {
                int roleId = RoleDao.getRoleIdByName(getRoleByEmployeeName(employee.getName()));
                String updateEmployeeRoleDetails = "UPDATE employee_role_details SET role_id = ? WHERE employee_id = ?";
                try (PreparedStatement pst1 = connection.prepareStatement(updateEmployeeRoleDetails)) {
                    pst1.setInt(1, roleId);
                    pst1.setInt(2, id);
                    pst1.executeUpdate();
                }
                
            }
	        String query = "UPDATE employee SET name = ?, email = ?, is_active = ?, password = ? WHERE id = ?";
	        try (PreparedStatement pst = connection.prepareStatement(query)) {
	            pst.setString(1, employee.getName());
	            pst.setString(2, employee.getEmail());
	            pst.setBoolean(3, employee.isStatus());
	            pst.setString(4, employee.getPassword());
	            pst.setInt(5, id);

	            int rows = pst.executeUpdate();

	            

	            return (rows > 0) ? true : false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DAOException(e.getMessage());
	    }
	}

	public static boolean deleteEmployee(Employee employee) throws DAOException, SQLException {
	    int deleteEmployeeRow = 0;
	    int id = EmployeeDao.getEmployeeIdByName(employee.getName());

	    try (Connection connection = ConnectionUtil.getConnection()) {
	        String deleteEmployeeDetails = "DELETE FROM employee_role_details WHERE employee_id = ?";
	        try (PreparedStatement pst = connection.prepareStatement(deleteEmployeeDetails)) {
	            pst.setInt(1, id);
	            int rows = pst.executeUpdate();

	            String deleteEmployee = "DELETE FROM employee WHERE id = ?";
	            try (PreparedStatement pst1 = connection.prepareStatement(deleteEmployee)) {
	                pst1.setInt(1, id);
	                deleteEmployeeRow = pst1.executeUpdate();
	            }

	            return (rows > 0 && deleteEmployeeRow > 0) ? true : false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DAOException(e.getMessage());
	    }
	}

	public static boolean readEmployee() throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM employee";
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) { // this will run the query and return the
					// value
					while (resultSet.next()) { // printing columns until there is no values

						logger.info("id: " + resultSet.getInt(1));
						logger.info("name: " + resultSet.getString(2));
						logger.info("email: " + resultSet.getString(3));
						logger.info("password: " + resultSet.getString(4));
						logger.info("date of joining: " + resultSet.getDate(5));
						logger.info("active: " + resultSet.getBoolean(6));
						logger.info("date of relieving: " + resultSet.getDate(7));
						logger.info("\n");

					}
					return true;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException(e);
			}

		}

	}

	public static boolean findEmployeeByName(String name) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM employee WHERE name = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, name);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {

						logger.info("id: " + resultSet.getInt(1));
						logger.info("name: " + resultSet.getString(2));
						logger.info("email: " + resultSet.getString(3));
						logger.info("password: " + resultSet.getString(4));
						logger.info("date_of_joining: " + resultSet.getString(5));
						logger.info("is_active: " + resultSet.getString(6));
						logger.info("date_of_relieving: " + resultSet.getString(7));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return true;
	}

}
