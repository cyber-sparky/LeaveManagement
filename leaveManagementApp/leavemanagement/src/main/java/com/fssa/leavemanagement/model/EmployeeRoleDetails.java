package com.fssa.leavemanagement.model;

public class EmployeeRoleDetails {
	private int id;
	private int employeeId;
	private int reportingManagerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public EmployeeRoleDetails(int id, int employeeId, int reportingManagerId) {
		this.id = id;
		this.employeeId = employeeId;
		this.reportingManagerId = reportingManagerId;
	}
}