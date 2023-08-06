package com.fssa.leavemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.leavemanagement.model.EmployeeRoleDetails;
import com.fssa.leavemanagement.util.ConnectionUtil;

public class EmployeeRoleDetailsDao {

	public static boolean addEmployeeRoleDetails(EmployeeRoleDetails erd) throws SQLException {
		String query = "INSERT INTO employee_role_details (employee_id,role_id,reporting_manager_id) VALUES (?,?,?);";
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, erd.getEmployeeId());
				pst.setInt(2, erd.getRoleId());
				pst.setInt(3, erd.getReportingManagerId());

				int rows = pst.executeUpdate();
				return (rows > 0) ? true : false;
			}
		}

	}

	public static boolean updateEmployeeRoleDetails(EmployeeRoleDetails erd) throws SQLException {
		String query = "UPDATE employee_role_details SET employee_id = ? , role_id = ? , reporting_manager_id = ? WHERE employee_id = ?";
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, erd.getEmployeeId());
				pst.setInt(2, erd.getRoleId());
				pst.setInt(3, erd.getReportingManagerId());
				pst.setInt(4, erd.getEmployeeId());

				int rows = pst.executeUpdate();
				return (rows > 0) ? true : false;
			}
		}
	}

	public static boolean getAllEmployeeRoleDetails() throws SQLException {
		String query = "SELECT * FROM employee_role_details";
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (Statement st = connection.createStatement()) {
				try (ResultSet rs = st.executeQuery(query)) {
					while (rs.next()) {
						System.out.println("id : " + rs.getInt("id"));
						System.out.println("id : " + rs.getInt("employee_id"));
						System.out.println("id : " + rs.getInt("role_id"));
						System.out.println("id : " + rs.getInt("reporting_manager_id"));
					}
					return true;
				}

			}
		}
	}
}