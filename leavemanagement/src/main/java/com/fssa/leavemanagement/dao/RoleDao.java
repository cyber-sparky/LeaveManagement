package com.fssa.leavemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;
import com.fssa.leavemanagement.util.ConnectionUtil;
import com.fssa.leavemanagement.util.Logger;
import com.fssa.leavemanagement.validator.RoleValidator;

public class RoleDao {

	static Logger logger = new Logger();

	public static boolean addRole(Role role) throws InvalidRoleException, SQLException {
		try {
			RoleValidator.validate(role);
		} catch (InvalidRoleException e) {
			e.printStackTrace();
			throw new InvalidRoleException("Invalid Role passed to DAO Layer");
		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO role(name) VALUES (?);";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, role.getName());

				int rows = pst.executeUpdate();

				return (rows > 0) ? true : false;
			}
		}
	}

	public static int getRoleIdByName(String name) throws SQLException {
		int id = 0;
		String query = "SELECT id FROM role WHERE name = ?";
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

	public static boolean deleteRole(Role role) throws InvalidRoleException, SQLException {

		int id = RoleDao.getRoleIdByName(role.getName());

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM role WHERE id= ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				int rows = pst.executeUpdate();

				return (rows > 0) ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidRoleException(e.getMessage());
		}
	}

	public static boolean readRole() throws InvalidRoleException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM role";
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) { // this will run the query and return the
																			// value
					while (resultSet.next()) { // printing columns until there is no values

						logger.info("id: " + resultSet.getInt(1));
						logger.info("name: " + resultSet.getString(2));
						logger.info("\n");

					}
					return true;
				}
			} catch (SQLException e) {

				throw new InvalidRoleException(e.getMessage());

			}

		}
	}

	public static boolean findRoleByName(String name) throws InvalidRoleException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM role WHERE name = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, name);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {

						logger.info("id: " + resultSet.getInt(1));
						logger.info("name: " + resultSet.getString(2));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new InvalidRoleException("Cannot find role");
			}
		}
		return true;
	}

}
