package com.cmi.lms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Bean;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.repository.BalanceRepo;
import com.cmi.lms.repository.EmpoyleeRepo;
import com.cmi.lms.repository.LeaveRepo;

@Service
public class EmployeeServiceRepo {

	@Autowired
	LeaveRepo leaverepo;
	@Autowired
	EmpoyleeRepo employeerepo;
	@Autowired
	BalanceRepo balancerepo;

	public Employee getManager(String employeeId) {
		Bean bean = new Bean();
		Optional<Employee> employee = employeerepo.findById(employeeId);

		return employee.get();
	}

	public String applyLeave(ApplyLeave applyleave) {

		leaverepo.save(applyleave);
		return "applied";
	}

	public ArrayList<ApplyLeave> grantLeave(String employeeId) {

	

		ArrayList<ApplyLeave> al = leaverepo.findLeaves(employeeId, "processing");

		return al;
	}

	public String updateforward(int sno, String managerId) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		employee.setEmployeeId(managerId);

		leaverepo.updateManager(sno, employee);
		return "updated";

	}

	public String updateStatus(String status, int sno) {
		// TODO Auto-generated method stub
		leaverepo.updateStatus(status, sno);
		if (status.equals("accept")) {
			Optional<ApplyLeave> applyleave = leaverepo.findById(sno);
			String leavetype = applyleave.get().getLeaveType();
			int days = applyleave.get().getNoOfDays();
			Employee employeeId = applyleave.get().getEmployeeId();

			if (leavetype.equals("LOP")) {
				int LOP = balancerepo.getLOP(employeeId);

				days = days + LOP;
				balancerepo.updateLOPCount(days, employeeId);
			} else if (leavetype.equals("Paid")) {
				int Paid = balancerepo.getPaid(employeeId);

				days = Paid - days;
				balancerepo.updatePaidCount(days, employeeId);
			}
		}
		return "updated";
	}

	public ArrayList<ApplyLeave> trackLeave(String employeeId) {
		

		ArrayList<ApplyLeave> al = leaverepo.trackLeaveDetails(employeeId);

		return al;
	}

	public ArrayList<BalanceLeaves> getLeaveBalance(String empid) {

		ArrayList<BalanceLeaves> arraylist = balancerepo.getBalanceLeaves(empid);

		return arraylist;
	}

	public String cancelLeave(int sno) {
		
		Optional<ApplyLeave> applyleave = leaverepo.findById(sno);
		if (applyleave.get().getStatus().equals("accept")) {
			ArrayList<BalanceLeaves> al = balancerepo
					.getBalanceLeaves(applyleave.get().getEmployeeId().getEmployeeId());
		
			if (applyleave.get().getLeaveType().equals("Paid")) {
				
				int Paid = al.get(0).getPaid();
				int days = Paid + applyleave.get().getNoOfDays();
				
				balancerepo.updatePaidCount(days, applyleave.get().getEmployeeId());
			} else if (applyleave.get().getLeaveType().equals("LOP")) {
				
				int LOP = al.get(0).getLOP();
				
				int days = LOP-applyleave.get().getNoOfDays() ;
				
				balancerepo.updateLOPCount(days, applyleave.get().getEmployeeId());
			}
		}
		leaverepo.cancelLeave(sno);
		return "canceled";
	}

	public ArrayList<ApplyLeave> validLeaves(Date startdate,String empid) {
	

		try {
			ArrayList<ApplyLeave> al = leaverepo.validLeaves(startdate,empid);
			return al;
		} catch (Exception e) {

			return null;
		}

	}

	public int validLOP(Date startdate, Date enddate,String employeeId) {

	
		int count = 0;
		int endmonth = enddate.getMonth() + 1;
		int strtmonth = startdate.getMonth() + 1;
		ArrayList<ApplyLeave> al = leaverepo.validLOP(strtmonth, endmonth, employeeId, "LOP","reject");
		for (int i = 0; i < al.size(); i++) {
			count = count + al.get(i).getNoOfDays();
		}
		return count;
	}

	public ArrayList<ApplyLeave> validLeavesEnd(Date enddate,String empid) {
	

		try {
			ArrayList<ApplyLeave> al = leaverepo.validLeavesEnd(enddate,empid);
			return al;
		} catch (Exception e) {

			return null;
		}
		
	}

	public ArrayList<ApplyLeave>  getSameDatesLeave(Date startdate, Date enddate,String employeeId) {


		try {
			ArrayList<ApplyLeave> al = leaverepo.sameDates(startdate,enddate, employeeId);
			return al;
		} catch (Exception e) {

			return null;
		}
		
	}
}
