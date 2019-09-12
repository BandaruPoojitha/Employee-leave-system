package com.cmi.lms.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.service.EmployeeServiceRepo;
import com.cmi.lms.service.validateLeave;

@RestController
@RequestMapping("/employeerest")
public class EmployeeOperationsRestController {
	@Autowired
	validateLeave validateLeave;
	@Autowired
	EmployeeServiceRepo employeedao;

	@GetMapping("/getmanagerId/{empid}")
	public Employee getManagerId(@PathVariable("empid") String employeeId) throws Exception {
		return employeedao.getManager(employeeId);

	}

	@PostMapping("/addleave")
	public String addLeave(@RequestBody ApplyLeave applyleave) {
		if (validateLeave.leaveValid(applyleave,applyleave.getEmployeeId().getEmployeeId()))
			return "applied";
		else
			return "notapplied";

	}

	@GetMapping("/grant/{empid}")
	public ArrayList<ApplyLeave> grantLeave(@PathVariable("empid") String empid) {

		return employeedao.grantLeave(empid);
	}

	@PutMapping("/forward")
	public String forward(@RequestBody ApplyLeave al) {
		return employeedao.updateforward(al.getSno(), al.getApplyTo().getManagerId());
	}

	@PutMapping("/status")
	public String status(@RequestBody ApplyLeave al) {
		return employeedao.updateStatus(al.getStatus(), al.getSno());
	}

	@GetMapping("/leavedetails/{empid}")
	public ArrayList<ApplyLeave> LeaveDetails(@PathVariable("empid") String empid) {
		return employeedao.trackLeave(empid);
		
	}
	@GetMapping("/cancel/{empid}")
	public ArrayList<ApplyLeave> cancelLeaveDetails(@PathVariable("empid") String empid) {
		return employeedao.trackLeave(empid);
	
	}
	@GetMapping("/balance/{empid}")
	public ArrayList<BalanceLeaves> balanceLeaves(@PathVariable("empid") String empid) {
		return employeedao.getLeaveBalance(empid);
	
		
	}

	@DeleteMapping("/cancelleave/{sno}")
	public String cancelLeave(@PathVariable("sno") int sno) throws Exception {

		return employeedao.cancelLeave(sno);
	}
}
