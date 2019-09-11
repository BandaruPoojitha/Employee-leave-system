package com.cmi.lms.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.bussiness.CancelLeaveValiation;
import com.cmi.lms.service.EmployeeServiceRepo;
import com.cmi.lms.service.validateLeave;

@RestController
@RequestMapping("/employeerest")
public class EmployeeOperationsRestController {
	@Autowired
	validateLeave validateLeave;
	@Autowired
	EmployeeServiceRepo employeedao;

	@RequestMapping("/getmanagerId/{empid}")
	public Employee getManagerId(@PathVariable("empid") String employeeId) {
		return employeedao.getManager(employeeId);

	}

	@RequestMapping("/addleave/{empid}")
	public String addLeave(@RequestBody ApplyLeave applyleave,@PathVariable("empid") String empid) {

		if (validateLeave.leaveValid(applyleave,empid))
			return "applied";
		else
			return "notapplied";

	}

	@RequestMapping("/grant/{empid}")
	public ArrayList<ApplyLeave> grantLeave(@PathVariable("empid") String empid) {

		return employeedao.grantLeave(empid);
	}

	@RequestMapping("/forward/{sno}/{managerId}")
	public String forward(@PathVariable("sno") int sno, @PathVariable("managerId") String managerId) {

		return employeedao.updateforward(sno, managerId);
	}

	@RequestMapping("/status/{sno}/{value}")
	public String status(@PathVariable("sno") int sno, @PathVariable("value") String status) {
		return employeedao.updateStatus(status, sno);
	}

	@RequestMapping("/leavedetails/{empid}")
	public ArrayList<ApplyLeave> LeaveDetails(@PathVariable("empid") String empid) {
		return employeedao.trackLeave(empid);
		
	}
	@RequestMapping("/cancel/{empid}")
	public ArrayList<ApplyLeave> cancelLeaveDetails(@PathVariable("empid") String empid) {
		return employeedao.trackLeave(empid);
	
	}
	@RequestMapping("/balance/{empid}")
	public ArrayList<BalanceLeaves> balanceLeaves(@PathVariable("empid") String empid) {
		return employeedao.getLeaveBalance(empid);
	
		
	}

	@RequestMapping("/cancelleave/{sno}")
	public String cancelLeave(@PathVariable("sno") int sno) {

		return employeedao.cancelLeave(sno);
	}
}
