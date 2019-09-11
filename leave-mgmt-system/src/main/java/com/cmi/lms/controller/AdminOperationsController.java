package com.cmi.lms.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cmi.lms.beans.Department;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.bussiness.ValidateEmployee;
import com.cmi.lms.service.AdminCallingRest;

@Controller
@RequestMapping("adminoperations")
public class AdminOperationsController {

	@Autowired
	AdminCallingRest adminRest;

	@RequestMapping(value = "addemployee", method = RequestMethod.POST)
	public String addEmployee(HttpSession session2, Employee employee,
			@RequestParam(name = "department") String department) {
		ValidateEmployee validateEmployee = new ValidateEmployee();
		Department department2 = new Department();

		department2.setDepartmentId(department);
		employee.setDepartment(department2);
		if (validateEmployee.emailValidation(employee.getEmail())) {
			if (validateEmployee.validatephonenumber(employee.getPhonenumber())) {
				String result = adminRest.addemployee(employee);
				if (result.equals("added")) {
					session2.setAttribute("addemployee", "added");
					return "redirect:/addemployee.jsp";
				} else {
					session2.setAttribute("addemployee", "notadded");
					return "redirect:/addemployee.jsp";
				}

			} else {
				session2.setAttribute("addemployee", "contact");
				return "redirect:/addemployee.jsp";
			}
		} else {
			session2.setAttribute("addemployee", "email");
			return "redirect:/addemployee.jsp";
		}
	}

	@RequestMapping(value = "adddepartment", method = RequestMethod.POST)
	public String addDepartment(HttpSession session1, Department department,String managerId) {

		String result = adminRest.addDepartment(department);
		if (result.equals("added"))
			session1.setAttribute("add", "added");
		return "redirect:/adddepartment.jsp";
	}

	@RequestMapping(value = "viewDepartment", method = RequestMethod.GET)
	public ModelAndView viewDepartment() {

		ArrayList<String> result = adminRest.viewDepartment();
		ModelAndView model = new ModelAndView("viewdepartment");
		model.addObject("viewdepartment", result);
		return model;
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editDetails(Employee employee) {

		ValidateEmployee ve = new ValidateEmployee();
		if (!employee.getEmployeeId().isEmpty()) {
			if (!employee.getAddress().isEmpty()) {
				adminRest.editAddress(employee);

			}
			if (!employee.getEmail().isEmpty()) {
				if (ve.emailValidation(employee.getEmail())) {
					adminRest.editEmail(employee);

				}
			}
			if (!employee.getPhonenumber().isEmpty()) {
				if (ve.validatephonenumber(employee.getPhonenumber())) {
					adminRest.editPhoneNumber(employee);

				}
			} else {
				return "redirect:/editEmployee.jsp";
			}
		} else {
			return "redirect:/editEmployee.jsp";
		}
		return "redirect:/editEmployee.jsp";
	}

}
