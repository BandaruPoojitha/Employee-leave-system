package com.cmi.lms.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.bussiness.CancelLeaveValiation;
import com.cmi.lms.bussiness.validateLeave;
import com.cmi.lms.service.EmployeeServiceRepo;

@RestController
@RequestMapping("/employeerest")
public class EmployeeOperationsRestController {
	@Autowired
	validateLeave validateLeave;
	@Autowired
	EmployeeServiceRepo employeedao;

	@RequestMapping("/getmanagerId")
	public String getManagerId() {
		return employeedao.getManager();

	}

	@RequestMapping("/addleave")
	public String addLeave(@RequestBody ApplyLeave applyleave) {

		if (validateLeave.leaveValid(applyleave))
			return "applied";
		else
			return "notapplied";

	}

	@RequestMapping("/grant")
	public ArrayList<String> grantLeave() {
		ArrayList<ApplyLeave> al = employeedao.grantLeave();
		ArrayList<String> arraylist = new ArrayList<>();
		for (int i = 0; i < al.size(); i++) {

			arraylist.add(al.get(i).getEmployeeId().getEmployeeId());
			arraylist.add(al.get(i).getLeaveType());
			arraylist.add(al.get(i).getStartdate().toString());
			arraylist.add(al.get(i).getEnddate().toString());
			arraylist.add(al.get(i).getApplyTo().getEmployeeId());
			arraylist.add(al.get(i).getReason());
			arraylist.add(al.get(i).getStatus());
			arraylist.add(String.valueOf(al.get(i).getSno()));
		}
		return arraylist;
	}

	@RequestMapping("/forward/{sno}/{managerId}")
	public String forward(@PathVariable("sno") int sno, @PathVariable("managerId") String managerId) {

		return employeedao.updateforward(sno, managerId);
	}

	@RequestMapping("/status/{sno}/{value}")
	public String status(@PathVariable("sno") int sno, @PathVariable("value") String status) {
		return employeedao.updateStatus(status, sno);
	}

	@RequestMapping("/leavedetails")
	public ArrayList<String> LeaveDetails() {
		ArrayList<ApplyLeave> al = employeedao.trackLeave();
		ArrayList<String> arraylist = new ArrayList<>();
		for (int i = 0; i < al.size(); i++) {

			arraylist.add(al.get(i).getEmployeeId().getEmployeeId());
			arraylist.add(al.get(i).getLeaveType());
			arraylist.add(al.get(i).getStartdate().toString());
			arraylist.add(al.get(i).getEnddate().toString());
			arraylist.add(al.get(i).getApplyTo().getEmployeeId());
			arraylist.add(al.get(i).getReason());
			arraylist.add(al.get(i).getStatus());
			arraylist.add(String.valueOf(al.get(i).getSno()));
		}
		return arraylist;
	}
	@RequestMapping("/cancel")
	public ArrayList<String> cancelLeaveDetails() {
		CancelLeaveValiation cancelleave = new CancelLeaveValiation();
		ArrayList<ApplyLeave> al = employeedao.trackLeave();
		ArrayList<String> arraylist = new ArrayList<>();
		for (int i = 0; i < al.size(); i++) {
			if (cancelleave.cancelLeave(al, i)) {
			arraylist.add(al.get(i).getEmployeeId().getEmployeeId());
			arraylist.add(al.get(i).getLeaveType());
			arraylist.add(al.get(i).getStartdate().toString());
			arraylist.add(al.get(i).getEnddate().toString());
			arraylist.add(al.get(i).getApplyTo().getEmployeeId());
			arraylist.add(al.get(i).getReason());
			arraylist.add(al.get(i).getStatus());
			arraylist.add(String.valueOf(al.get(i).getSno()));
		}
		}
		return arraylist;
	}
	@RequestMapping("/balance")
	public ArrayList<Integer> balanceLeaves() {
		ArrayList<BalanceLeaves> arraylist = employeedao.getLeaveBalance();
		ArrayList<Integer> arrayList2 = new ArrayList<>();
		arrayList2.add(arraylist.get(0).getLOP());
		arrayList2.add(arraylist.get(0).getPaid());
		return arrayList2;
	}

	@RequestMapping("/cancelleave/{sno}")
	public String cancelLeave(@PathVariable("sno") int sno) {

		return employeedao.cancelLeave(sno);
	}
}
