package com.cmi.lms.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.lms.beans.Department;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.beans.Login;
import com.cmi.lms.service.AdminServiceRepo;

@RestController
@RequestMapping("/adminrest")
public class AdminOperationsRestController {
	@Autowired
	AdminServiceRepo admin;

	@PostMapping(value = "/createemployee")
	public String employeeCreate(@RequestBody Employee employee) {
		String result = admin.addEmployee(employee);
		return result;
	}
	@GetMapping(value = "/getempid")
	@ResponseBody
	public ArrayList<Login> getEmpId() {
		
		return admin.getEmployeeId();

	}
	@PostMapping(value = "/adddepartment")
	public String addDepartment(@RequestBody Department department) {
		System.out.println("dpt  "+department.getManagerId());
		admin.addDepartment(department);
		return "added";
	}

	@GetMapping("/viewdepartment")
	public ArrayList<Department> viewDepartment() throws Exception{
	return  admin.viewDepartment();
	
	}

	@PutMapping("/editaddress")
	public String editAddress(@RequestBody Employee employee) {
		admin.editAddress(employee.getAddress(), employee.getEmployeeId());
		return "editdaddress";
	}

	@PutMapping("/editemail")
	public String editEmail(@RequestBody Employee employee) {
		admin.editEmail(employee.getEmail(), employee.getEmployeeId());
		return "editedemail";
	}

	@PutMapping("/editcontact")
	public String editContact(@RequestBody Employee employee) {
		admin.editContact(employee.getPhonenumber(), employee.getEmployeeId());
		return "editdcontact";
	}
}
