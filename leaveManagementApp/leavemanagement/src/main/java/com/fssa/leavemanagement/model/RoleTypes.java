package com.fssa.leavemanagement.model;

public enum RoleTypes {
	CEO("CEO"), MANAGER("Manager"), TEAMLEAD("Team Lead"), HR("HR");

	private String getName;

	RoleTypes(String name) {
		this.getName = name;
	}

	public String getName() {
		return getName;
	}

}
