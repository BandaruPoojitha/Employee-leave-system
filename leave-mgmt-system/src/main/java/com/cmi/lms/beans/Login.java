package com.cmi.lms.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Login {
	@Id
	private String username;
	@OneToOne
	private Employee employeeId;

	@Override
	public String toString() {
		return "Login [username=" + username + ", employeeId=" + employeeId + ", password=" + password
				+ ", employeeType=" + employeeType + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	String password;
	String employeeType;

}
