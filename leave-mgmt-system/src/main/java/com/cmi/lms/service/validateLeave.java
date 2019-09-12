package com.cmi.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.util.ApplicationUtil;

@Component
public class validateLeave {

	@Autowired
	EmployeeServiceRepo employeedao;
	static int dayscount = 0;
	ArrayList<ApplyLeave> arraylist = null;

	boolean strechLeavesAfter(Date enddate, int noofdays,String empid) {
		try {
			while (noofdays + dayscount < 5) {
				arraylist = employeedao.validLeavesEnd(enddate,empid);
				if (arraylist.isEmpty()) {
					return true;
				} else {

					dayscount += arraylist.get(0).getNoOfDays();
					enddate = (Date) arraylist.get(0).getEnddate();

				}
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	boolean strechLeavesBefore(Date startdate, int noofdays,String empid) {
		try {
			while (noofdays + dayscount < 5) {
				arraylist = employeedao.validLeaves(startdate,empid);
				if (arraylist.isEmpty()) {
					return true;
				} else {
					dayscount += arraylist.get(0).getNoOfDays();
					startdate = (Date) arraylist.get(0).getStartdate();

				}
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public boolean leaveValid(ApplyLeave applyleave,String employeeId) {

	ApplicationUtil au=new ApplicationUtil();
		int noofdays = (int) au.daysBetween(applyleave.getStartdate(), applyleave.getEnddate());
		@SuppressWarnings("deprecation")
		int year = applyleave.getStartdate().getYear() + 1900;
		if ((year == LocalDate.now().getYear())) {
			if(sameDates(applyleave.getStartdate(),applyleave.getEnddate(),employeeId)){
			if (strechLeavesBefore(applyleave.getStartdate(), noofdays,employeeId)) {
				if (strechLeavesAfter(applyleave.getEnddate(), noofdays,employeeId)) {
					ArrayList<BalanceLeaves> al = employeedao.getLeaveBalance(employeeId);
					if (applyleave.getLeaveType().equals("Paid")) {

						if (al.get(0).getPaid() >= noofdays) {
							employeedao.applyLeave(applyleave);
						} else if (al.get(0).getPaid() < noofdays) {
							applyleave.setLeaveType("LOP");
							if (noofdays <= 3) {
								int totalLop = employeedao.validLOP(applyleave.getStartdate(), applyleave.getEnddate(),employeeId);
								if ((totalLop + noofdays) <= 3) {
									employeedao.applyLeave(applyleave);
								}
							}
						}
					} else if (applyleave.getLeaveType().equals("LOP")) {
						if (noofdays <= 3) {
							int totalLop = employeedao.validLOP(applyleave.getStartdate(), applyleave.getEnddate(),employeeId);
							if ((totalLop + noofdays) <= 3) {
								employeedao.applyLeave(applyleave);
							} else {
								return false;
							}
						}
					}

				}

				else {

					return false;
				}
			} else {
				return false;
			}
		
			}else {
			return false;
		}
		}
		else {
			return false;
		}
		return true;
	
	}

	private boolean sameDates(Date startdate, Date enddate,String employeeId) {
		
		ArrayList<ApplyLeave> arraylist = employeedao.getSameDatesLeave( startdate,enddate,employeeId);
		if(arraylist.isEmpty())
			return true;
		else
		return false;
	}
}