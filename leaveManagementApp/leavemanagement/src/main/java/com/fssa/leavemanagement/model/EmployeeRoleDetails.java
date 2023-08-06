package com.fssa.leavemanagement.model;

public class EmployeeRoleDetails {
	private int roleId;
	private int employeeId;
	private int reportingManagerId;
	
	
	public EmployeeRoleDetails() {
		/**
		 * default constructor
		 */
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(int reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public EmployeeRoleDetails(int roleId, int employeeId, int reportingManagerId) {

		this.roleId = roleId;
		this.employeeId = employeeId;
		this.reportingManagerId = reportingManagerId;
	}

}