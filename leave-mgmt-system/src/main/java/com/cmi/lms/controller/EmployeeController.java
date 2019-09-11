package com.cmi.lms.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.service.EmployeeCallingRest;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeCallingRest employeecallingRest;
	@RequestMapping(value = "applyleave", method = RequestMethod.GET)
	public String createEmployee() {
		return "redirect:/applyleave.jsp";
	}

	@RequestMapping(value = "grantleave", method = RequestMethod.GET)
	public String grantEmployee(HttpSession session) {
		String empid=(String) session.getAttribute("empid");
	ArrayList<ApplyLeave> arraylist=employeecallingRest.grantLeave(empid);

		session.setAttribute("list", arraylist);
		return "redirect:/grantleave.jsp";

	}

	@RequestMapping(value = "cancelleave", method = RequestMethod.GET)
	public ModelAndView cancel(HttpSession session) {
		String empid=(String) session.getAttribute("empid");
		ArrayList<ApplyLeave> arraylist =employeecallingRest.cancelLeave(empid);
		ModelAndView model = new ModelAndView("cancelleave");
		model.addObject("cancel", arraylist);
		return model;
	}

	@RequestMapping(value = "track", method = RequestMethod.GET)
	public ModelAndView trackLeave(HttpSession session) {
		String empid=(String) session.getAttribute("empid");
		
		ArrayList<ApplyLeave> arraylist =employeecallingRest.trackLeave(empid);

		ModelAndView model = new ModelAndView("TrackLeave");
		model.addObject("track", arraylist);
		return model;
	}

	@RequestMapping(value = "/balanceleaves", method = RequestMethod.GET)
	public ModelAndView balanceLeaves(HttpSession session) {
		String empid=(String) session.getAttribute("empid");
		ArrayList<BalanceLeaves> arraylist =employeecallingRest.balanceLeaves(empid);
		ModelAndView model = new ModelAndView("BalanceLeaves");
		model.addObject("balance", arraylist);
		return model;
	}
}
