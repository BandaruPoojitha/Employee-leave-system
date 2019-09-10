package com.cmi.lms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView grantEmployee() {
		ArrayList<String> arraylist=employeecallingRest.grantLeave();
		ModelAndView model = new ModelAndView("grantleave");
		model.addObject("list", arraylist);
		return model;

	}

	@RequestMapping(value = "cancelleave", method = RequestMethod.GET)
	public ModelAndView cancel() {
		ArrayList<String> arraylist =employeecallingRest.cancelLeave();
//		RestTemplate restTemplate = new RestTemplate();
//		String uri = restURI.getURL()+"/employeerest/cancel";
//		ArrayList<String> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		ModelAndView model = new ModelAndView("cancelleave");
		model.addObject("cancel", arraylist);
		return model;
	}

	@RequestMapping(value = "track", method = RequestMethod.GET)
	public ModelAndView trackLeave() {
		ArrayList<String> arraylist =employeecallingRest.trackLeave();
//		RestTemplate restTemplate = new RestTemplate();
//		String uri =restURI.getURL()+"/employeerest/leavedetails";
//		ArrayList<String> arraylist = restTemplate.getForObject(uri, ArrayList.class);

		ModelAndView model = new ModelAndView("TrackLeave");
		model.addObject("track", arraylist);
		return model;
	}

	@RequestMapping(value = "/balanceleaves", method = RequestMethod.GET)
	public ModelAndView balanceLeaves() {
		ArrayList<Integer> arraylist =employeecallingRest.balanceLeaves();
//		RestTemplate restTemplate = new RestTemplate();
//		String uri = restURI.getURL()+"/employeerest/balance";
//		ArrayList<Integer> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		ModelAndView model = new ModelAndView("BalanceLeaves");
		model.addObject("balance", arraylist);
		return model;
	}
}
