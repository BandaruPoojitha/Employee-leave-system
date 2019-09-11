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
		Employee employee=new Employee();
		Employee employee1=new Employee();
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
		System.out.println(session.getAttribute("empid")+"{{{{{{{");
		String employeeId= session.getAttribute("empid").toString();
		employee1.setEmployeeId(employeeId);
		ApplyLeave al = new ApplyLeave();
		al.setStartdate(from);
		al.setEnddate(to);
		al.setReason(reason);
		al.setEmployeeId(employee1);
		al.setLeaveType(LeaveType);
		String managerId=employeecallingRest.getManagerId(employeeId);
		employee.setEmployeeId(managerId);
		al.setApplyTo(employee);
		al.setStatus("processing");
		al.setNoOfDays((int) DaysCount.daysBetween(from, to));
		String result=employeecallingRest.applyLeave(al,employeeId);
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
		ArrayList<ApplyLeave> arraylist = (ArrayList<ApplyLeave>) session.getAttribute("updatelist");
		String employeeId= session.getAttribute("empid").toString();
		
		for (int i = 0; i < arraylist.size(); i++) {
			String i1 = new Integer(i).toString();
			String value = request.getParameter("JK" + i1);
		
			int sno = arraylist.get(i).getSno();

			if (value.equals("forward")) {
				String managerId=employeecallingRest.getManagerId(employeeId);

				employeecallingRest.forwardLeave(sno,managerId);

				
			} else if (value.equals("accept") || value.equals("reject")) {
			employeecallingRest.updateStatus(sno,value);

				
			}
			
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
		ArrayList<ApplyLeave> arraylist = (ArrayList<ApplyLeave>) session.getAttribute("cancellist");

		for (int i = 0; i < arraylist.size(); i++) {
			String i1 = new Integer(i).toString();
			String value = request.getParameter("jk" + i1);
			int sno =arraylist.get(i).getSno();
			if (value.equals("cancel")) {
				employeecallingRest.cancel(sno);

			} 
		}
		}catch(Exception e) {
			return "redirect:/cancelleave.jsp";
		}
		return "redirect:/cancelleave.jsp";
	}

}
