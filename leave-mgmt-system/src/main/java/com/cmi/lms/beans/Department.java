package com.cmi.lms.beans;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Department {
@Id
String departmentId;
@OneToMany(mappedBy = "department")
private List<Employee> createmployee=new ArrayList<Employee>();

String managerId;
public String getDepartmentId() {
	return departmentId;
}
public void setDepartmentId(String departmentId) {
	this.departmentId = departmentId;
}
public String getManagerId() {
	return managerId;
}
public void setManagerId(String managerId) {
	this.managerId = managerId;
}

//	@Override
//	public String toString() {
//		return "Department [departmentId=" + departmentId + ", createmployee=" + createmployee + ", managerId="
//				+ managerId + "]";
//	}

}
