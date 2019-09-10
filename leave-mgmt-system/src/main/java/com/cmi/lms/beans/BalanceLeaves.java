package com.cmi.lms.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BalanceLeaves {
	@Id
	@GeneratedValue
	int serialno;
	@OneToOne(targetEntity = Employee.class)
	Employee employeeId;
	int LOP;
	int Paid;

	public int getSerialno() {
		return serialno;
	}

	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public int getLOP() {
		return LOP;
	}

	public void setLOP(int lOP) {
		LOP = lOP;
	}

	public int getPaid() {
		return Paid;
	}

	public void setPaid(int paid) {
		Paid = paid;
	}
}
