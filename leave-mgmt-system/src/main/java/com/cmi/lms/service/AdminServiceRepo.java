package com.cmi.lms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Department;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.beans.Login;
import com.cmi.lms.exception.LMSUnAuthorisedException;
import com.cmi.lms.repository.BalanceRepo;
import com.cmi.lms.repository.DepartmentRepo;
import com.cmi.lms.repository.EmpoyleeRepo;
import com.cmi.lms.repository.LoginRepo;

@Service
public class AdminServiceRepo{
	@Autowired
	DepartmentRepo departmentrepo;
	@Autowired
	EmpoyleeRepo employeerepo;
	@Autowired
	LoginRepo loginrepo;
	@Autowired
	BalanceRepo balancerepo;

	public String addEmployee(Employee employee) {

		Login login = new Login();
		BalanceLeaves balanceleave = new BalanceLeaves();
		login.setEmployeeId(employee);
		login.setEmployeeType("employee");
		login.setUsername(employee.getEmployeeName() + employee.getEmployeeId());
		login.setPassword(employee.getEmployeeId() + employee.getEmployeeName());
		balanceleave.setEmployeeId(employee);
		balanceleave.setLOP(0);
		balanceleave.setPaid(10);
		employeerepo.save(employee);
		loginrepo.save(login);
		balancerepo.save(balanceleave);

		return "added";
	}

	public void addDepartment(Department department) {
		loginrepo.updaterole(department.getManagerId(), "manager");
		departmentrepo.save(department);

	}

	public ArrayList<Department> viewDepartment() {
		ArrayList<Department> al = (ArrayList<Department>) departmentrepo.findAll();

		if (al == null) {
			throw new LMSUnAuthorisedException("Invalid Credentials");
		}
		return al;
	}

	public void editAddress(String address, String employeeId) {
		employeerepo.editAddress(address, employeeId);
	}

	public void editEmail(String email, String employeeId) {
		employeerepo.editEmail(email, employeeId);
	}

	public void editContact(String contact, String employeeId) {
		employeerepo.editContact(contact, employeeId);
	}

	public ArrayList<Login> getEmployeeId() {
		
	return loginrepo.getEmployeeid();
		
	}

}
