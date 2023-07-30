package com.fssa.leavemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.fssa.leavemanagement.exceptions.DAOException;
import com.fssa.leavemanagement.exceptions.InvalidEmployeeException;
import com.fssa.leavemanagement.model.Employee;
import com.fssa.leavemanagement.model.EmployeeErrors;
import com.fssa.leavemanagement.validator.EmployeeValidator;

public class EmployeeDao {
	public static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

	public static void main(String[] args) throws InvalidEmployeeException {
		Employee emp = new Employee();
		emp.setDateOfJoin(LocalDate.now());
		emp.setEmail("saleela@gmail.com");
		emp.setName("saleela");
		emp.setPassword("        ");
		emp.setStatus(true);
		addEmployee(emp);
	}

	public static boolean addEmployee(Employee employee) throws InvalidEmployeeException {
		try {
			EmployeeValidator.validateEmployee(employee);
		} catch (InvalidEmployeeException e) {
			e.printStackTrace();
			throw new InvalidEmployeeException("Invalid employee passed to DAO Layer", e);
		}

		
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO employee(name,email,date_of_joining, is_active,password) VALUES (?,?,?,?,?);";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, employee.getName());
				pst.setString(2, employee.getEmail());
				pst.setDate(3, java.sql.Date.valueOf(employee.getDateOfJoin()));
				pst.setBoolean(4, employee.isStatus());
				pst.setString(5, employee.getPassword());
//				pst.setDate(5, java.sql.Date.valueOf(employee.getDateOfRelieve()));

				int rows = pst.executeUpdate();
				return (rows > 0) ? true : false;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidEmployeeException(EmployeeErrors.CANNOT_ADD_EMPLOYEE);
		}

	}

	public static boolean updateEmployee(Employee employee, int id) throws DAOException {
		try {
			EmployeeValidator.validateEmployee(employee);
		} catch (InvalidEmployeeException e) {
			e.printStackTrace();
			throw new DAOException("Invalid employee passed to DAO Layer", e);
		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE employee SET email = ? WHERE id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, employee.getName());
				
				pst.setInt(2, id);
//				pst.setDate(5, java.sql.Date.valueOf(employee.getDateOfRelieve()));

				int rows = pst.executeUpdate();

				return (rows > 0) ? true : false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}
	public static boolean deleteEmployee(int id) throws DAOException, InvalidEmployeeException {
		
		EmployeeValidator.validateId(id);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM employee WHERE id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				
				pst.setInt(1, id);

				int rows = pst.executeUpdate();

				return (rows > 0) ? true : false;
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

						LOGGER.info("id: " + resultSet.getInt(1));
						LOGGER.info("name: " + resultSet.getString(2));
						LOGGER.info("email: " + resultSet.getString(3));
						LOGGER.info("password: " + resultSet.getString(4));
						LOGGER.info("date of joining: " + resultSet.getDate(5));
						LOGGER.info("active: " + resultSet.getBoolean(6));
						LOGGER.info("date of relieving: " + resultSet.getDate(7));
						LOGGER.info("\n");

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

						LOGGER.info("id: " + resultSet.getInt(1));
						LOGGER.info("name: " + resultSet.getString(2));
						LOGGER.info("email: " + resultSet.getString(3));
						LOGGER.info("password: " + resultSet.getString(4));
						LOGGER.info("date_of_joining: " + resultSet.getString(5));
						LOGGER.info("is_active: " + resultSet.getString(6));
						LOGGER.info("date_of_relieving: " + resultSet.getString(7));
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
