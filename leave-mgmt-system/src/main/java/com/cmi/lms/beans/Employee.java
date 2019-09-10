package com.cmi.lms.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Employee {
	@Id
	String employeeId;
	
	@ManyToOne
	private Department department;
	String employeeName;
	String managerId;
	
	String email;
	String address;
	String phonenumber;


	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", department=" + department + ", employeeName=" + employeeName
				+ ", managerId=" + managerId + ", email=" + email + ", address=" + address + ", phonenumber="
				+ phonenumber + "]";
	}

	
}
