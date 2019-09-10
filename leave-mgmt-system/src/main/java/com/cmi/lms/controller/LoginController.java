package com.cmi.lms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmi.lms.beans.Login;
import com.cmi.lms.service.LoginCallRest;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginCallRest logincallRest;
	@ResponseBody
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public ModelAndView getUser(HttpSession session, Login login) throws ServletException, IOException {
	
		return validateRequest(session, login);
		
	}
	/*
	 * Validate the request to make sure username and password
	 */
	private ModelAndView validateRequest(HttpSession session, Login login) {
		ModelAndView modelAndView;
	
		if (!login.getUsername().isEmpty()) {
			if (!login.getPassword().isEmpty()) {
				Login result =logincallRest.getLoginDetails(login);
				if (result.getEmployeeType().equals("admin")) {
					modelAndView = new ModelAndView("/admin");
				} else if (result.getEmployeeType().equals("employee") || result.getEmployeeType().equals("manager") || result.getEmployeeType().equals("CEO")) {
	               session.setAttribute("role", result.getEmployeeType());
					session.setAttribute("empid", result.getEmployeeId().getEmployeeId());
					modelAndView = new ModelAndView("/employee");
				} else {
					modelAndView = new ModelAndView("/Login");
				}
				return modelAndView;
			} else {
				session.setAttribute("password", "nullpassword");
				modelAndView = new ModelAndView("Login");
			}
		} else {
			session.setAttribute("username", "nullusername");
			modelAndView = new ModelAndView("Login");
		}
		return modelAndView;
	}

}
