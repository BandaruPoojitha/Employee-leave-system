package com.cmi.lms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.service.EmployeeCallingRest;
import com.cmi.lms.util.ApplicationUtil;

@Controller
@RequestMapping("/employeeoperations")
public class EmployeeOperationsController {
	@Autowired
	EmployeeCallingRest employeecallingRest;

	@RequestMapping(value = "/addleave", method = RequestMethod.POST)
	public String addLeave(HttpSession session, @RequestParam("LeaveType") String LeaveType,
			@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate,
			@RequestParam("reason") String reason) throws ParseException {
		ApplicationUtil au=new ApplicationUtil();
		Employee employee = new Employee();
		Employee employee1 = new Employee();
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
		String employeeId = session.getAttribute("empid").toString();
		employee1.setEmployeeId(employeeId);
		ApplyLeave al = new ApplyLeave();
		al.setStartdate(from);
		al.setEnddate(to);
		al.setReason(reason);
		al.setEmployeeId(employee1);
		al.setLeaveType(LeaveType);
		try {
		Employee result1 = employeecallingRest.getManagerId(employeeId);
		String managerId = result1.getManagerId();
		employee.setEmployeeId(managerId);
		al.setApplyTo(employee);
		al.setStatus("processing");
		al.setNoOfDays((int)au.daysBetween(from, to));
		String result = employeecallingRest.applyLeave(al);
		if (result.equals("applied"))
			session.setAttribute("status", "applied");
		else
			session.setAttribute("status", "notapplied");
		}catch(Exception e) {
			return "redirect:/applyleave.jsp";
		}
		return "redirect:/applyleave.jsp";
	}

	@RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
	public String updateStatus(HttpSession session, HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<ApplyLeave> arraylist = (ArrayList<ApplyLeave>) session.getAttribute("updatelist");
			String employeeId = session.getAttribute("empid").toString();

			for (int i = 0; i < arraylist.size(); i++) {
				String value = request.getParameter("JK" + i);
				int sno = arraylist.get(i).getSno();
				if (value.equals("forward")) {
					Employee result1 = employeecallingRest.getManagerId(employeeId);
					ApplyLeave al=new ApplyLeave();
					al.setSno(sno);
					al.setApplyTo(result1);
					employeecallingRest.forwardLeave(al);

				} else if (value.equals("accept") || value.equals("reject")) {
					ApplyLeave al=new ApplyLeave();
					al.setSno(sno);
					al.setStatus(value);
					employeecallingRest.updateStatus(al);

				}

			}
		} catch (Exception e) {
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
				String value = request.getParameter("jk" + i);
				int sno = arraylist.get(i).getSno();
				if (value.equals("cancel")) {
					employeecallingRest.cancel(sno);

				}
			}
		} catch (Exception e) {
			return "redirect:/employee.jsp";
		}
		return "redirect:/cancelleave.jsp";
	}

}
