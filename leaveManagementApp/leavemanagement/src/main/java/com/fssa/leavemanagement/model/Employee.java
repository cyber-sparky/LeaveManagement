package com.fssa.leavemanagement.model;

import java.time.LocalDate;

public class Employee {
	private String name;
	private String email;
	private String password;
	LocalDate dateOfJoin;
	boolean status;
	LocalDate dateOfRelieve;
	Employee manager;
	
//	Default Constructor
	public Employee() {
		
	}

	public Employee(String name, String email, String password, LocalDate dateOfJoin, boolean status,
			LocalDate dateOfRelieve, Employee manager) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.dateOfJoin = dateOfJoin;
		this.status = status;
		this.dateOfRelieve = dateOfRelieve;
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(LocalDate dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDate getDateOfRelieve() {
		return dateOfRelieve;
	}

	public void setDateOfRelieve(LocalDate dateOfRelieve) {
		this.dateOfRelieve = dateOfRelieve;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Employee getManager() {
		return manager;
	}
}
