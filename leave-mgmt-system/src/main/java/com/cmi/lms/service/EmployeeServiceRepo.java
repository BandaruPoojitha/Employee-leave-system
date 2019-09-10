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

	public String getManager() {
		Bean bean = new Bean();
		Optional<Employee> employee = employeerepo.findById(bean.getEmployeeId());

		return employee.get().getManagerId();
	}

	public String applyLeave(ApplyLeave applyleave) {

		leaverepo.save(applyleave);
		return "aapled";
	}

	public ArrayList<ApplyLeave> grantLeave() {

		Bean bean = new Bean();
		Employee emp = new Employee();
		emp.setEmployeeId(bean.getEmployeeId());

		ArrayList<ApplyLeave> al = leaverepo.findLeaves(emp, "processing");

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

	public ArrayList<ApplyLeave> trackLeave() {
		// TODO Auto-generated method stub
		Bean bean = new Bean();
		Employee employee = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());

		ArrayList<ApplyLeave> al = leaverepo.trackLeaveDetails(employee);

		return al;
	}

	public ArrayList<BalanceLeaves> getLeaveBalance() {
		// TODO Auto-generated method stub

		Bean bean = new Bean();
		Employee employee = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());
		ArrayList<BalanceLeaves> arraylist = balancerepo.getBalanceLeaves(bean.getEmployeeId());

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

	public ArrayList<ApplyLeave> validLeaves(Date startdate) {
	
		Bean bean = new Bean();
		Employee employee = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());

		try {
			ArrayList<ApplyLeave> al = leaverepo.validLeaves(startdate, bean.getEmployeeId());
			return al;
		} catch (Exception e) {

			return null;
		}

	}

	public int validLOP(Date startdate, Date enddate) {

		Bean bean = new Bean();
		Employee employee = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());
		int count = 0;
		int endmonth = enddate.getMonth() + 1;
		int strtmonth = startdate.getMonth() + 1;
		ArrayList<ApplyLeave> al = leaverepo.validLOP(strtmonth, endmonth, employee, "LOP","reject");
		for (int i = 0; i < al.size(); i++) {
			count = count + al.get(i).getNoOfDays();
		}
		return count;
	}

	public ArrayList<ApplyLeave> validLeavesEnd(Date enddate) {
	
		Bean bean = new Bean();
		Employee employee = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());

		try {
			ArrayList<ApplyLeave> al = leaverepo.validLeavesEnd(enddate, bean.getEmployeeId());
			return al;
		} catch (Exception e) {

			return null;
		}
		
	}

	public ArrayList<ApplyLeave>  getSameDatesLeave(Date startdate, Date enddate) {

		Bean bean = new Bean();
		Employee employee = new Employee();
		employee.setEmployeeId(bean.getEmployeeId());

		try {
			ArrayList<ApplyLeave> al = leaverepo.sameDates(startdate,enddate, bean.getEmployeeId());
			return al;
		} catch (Exception e) {

			return null;
		}
		
	}
}
