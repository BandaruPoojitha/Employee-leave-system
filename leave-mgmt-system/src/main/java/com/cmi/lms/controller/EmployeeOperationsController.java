package com.cmi.lms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.Bean;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.bussiness.DaysCount;
import com.cmi.lms.service.EmployeeCallingRest;

@Controller
@RequestMapping("/employeeoperations")
public class EmployeeOperationsController {
	@Autowired
	EmployeeCallingRest employeecallingRest;
	@RequestMapping(value = "/addleave", method = RequestMethod.POST)
	public String addLeave(HttpSession session, @RequestParam("LeaveType") String LeaveType,
			@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate,
			@RequestParam("reason") String reason) throws ParseException {
		Bean bean = new Bean();
		Employee employee = new Employee();
		Employee employee1 = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
		ApplyLeave al = new ApplyLeave();
		al.setStartdate(from);
		al.setEnddate(to);
		al.setReason(reason);
		al.setEmployeeId(employee);
		al.setLeaveType(LeaveType);
		String managerId=employeecallingRest.getManagerId();
//		String uri = restURI.getURL()+"/employeerest/getmanagerId";
//		RestTemplate rt = new RestTemplate();
//		String managerId = rt.getForObject(uri, String.class);
		employee1.setEmployeeId(managerId);
		al.setApplyTo(employee1);
		al.setStatus("processing");
		al.setNoOfDays((int) DaysCount.daysBetween(from, to));
		String result=employeecallingRest.applyLeave(al);
//		String uri1 =restURI.getURL()+"/employeerest/addleave";
//		RestTemplate resttemplate = new RestTemplate();
//		String result = resttemplate.postForObject(uri1, al, String.class);
		if (result.equals("applied"))
			session.setAttribute("status", "applied");
		else
			session.setAttribute("status", "notapplied");
		return "redirect:/applyleave.jsp";
	}

	@RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
	public String updateStatus(HttpSession session, HttpServletRequest request) {
		try {
		@SuppressWarnings("unchecked")
		ArrayList<String> arraylist = (ArrayList<String>) session.getAttribute("updatelist");
		
		int j = 7;
		for (int i = 0; i < arraylist.size(); i++) {
			String i1 = new Integer(i).toString();
			String value = request.getParameter("JK" + i1);
		
			int sno = Integer.parseInt(arraylist.get(j));

			if (value.equals("forward")) {
				String managerId=employeecallingRest.getManagerId();
//				String uri1 = "http://localhost:8083/employeerest/getmanagerId";
//				RestTemplate resttemplate = new RestTemplate();
//				String managerId = resttemplate.getForObject(uri1, String.class);
				employeecallingRest.forwardLeave(sno,managerId);
//				String uri = restURI.getURL()+"/employeerest/forward/" + sno + "/" + managerId;
//				String result = resttemplate.getForObject(uri, String.class);
				j = j + 8;
			} else if (value.equals("accept") || value.equals("reject")) {
			employeecallingRest.updateStatus(sno,value);
//				RestTemplate resttemplate = new RestTemplate();
//				String uri =restURI.getURL()+"/employeerest/status/" + sno + "/" + value;
//				String result = resttemplate.getForObject(uri, String.class);
				j = j + 8;
			}
			i = i + 7;
		}
		}catch(Exception e) {
			return "redirect:/grantleave.jsp";
		}
		return "redirect:/grantleave.jsp";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String cancleLeave(HttpSession session, HttpServletRequest request) {
		try {
		@SuppressWarnings("unchecked")
		ArrayList<String> arraylist = (ArrayList<String>) session.getAttribute("cancellist");
		int j = 7;

		for (int i = 0; i < arraylist.size(); i++) {
			String i1 = new Integer(i).toString();
			String value = request.getParameter("jk" + i1);
			int sno = Integer.parseInt(arraylist.get(j));
			if (value.equals("cancel")) {
				employeecallingRest.cancel(sno);
//				RestTemplate resttemplate = new RestTemplate();
//				String uri = restURI.getURL()+"/employeerest/cancelleave/" + sno;
//				String result = resttemplate.getForObject(uri, String.class);
				j = j + 8;
			} else {
				j = j + 8;
			}
			i = i + 7;

		}
		}catch(Exception e) {
			return "redirect:/cancelleave.jsp";
		}
		return "redirect:/cancelleave.jsp";
	}

}
