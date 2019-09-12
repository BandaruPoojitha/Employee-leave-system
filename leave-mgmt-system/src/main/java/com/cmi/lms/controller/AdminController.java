package com.cmi.lms.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmi.lms.beans.Login;
import com.cmi.lms.service.AdminCallingRest;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminCallingRest adminRest;

	@RequestMapping(value = "createemployee", method = RequestMethod.GET)
	public String createEmployee(HttpSession session) {
		ArrayList<Login> arraylist = adminRest.getEmployeeId();
		session.setAttribute("empids", arraylist);
		return "redirect:/addemployee.jsp";
	}

	@RequestMapping(value = "adddepartment", method = RequestMethod.GET)
	public String addDepartment(HttpSession session) {
		ArrayList<Login> arraylist = adminRest.getEmployeeId();
		session.setAttribute("empids", arraylist);
		return "redirect:/adddepartment.jsp";
	}

	@RequestMapping(value = "editEmployee", method = RequestMethod.GET)
	public String editEmployee() {
		return "redirect:/editEmployee.jsp";
	}
}
