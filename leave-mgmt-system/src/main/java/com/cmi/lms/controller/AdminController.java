package com.cmi.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
@RequestMapping(value="createemployee",method = RequestMethod.GET)
public String createEmployee() {
	
	return "redirect:/addemployee.jsp";
}
@RequestMapping(value="adddepartment",method = RequestMethod.GET)
public String addDepartment() {
	return "redirect:/adddepartment.jsp";
}
@RequestMapping(value="editEmployee",method = RequestMethod.GET)
public String editEmployee() {
	return "redirect:/editEmployee.jsp";
}
}
