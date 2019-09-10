package com.cmi.lms.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class ApplyLeave {
    @Override
	public String toString() {
		return "ApplyLeave [sno=" + sno + ", employeeId=" + employeeId + ", LeaveType=" + LeaveType + ", startdate="
				+ startdate + ", enddate=" + enddate + ", applyTo=" + applyTo + ", reason=" + reason + ", status="
				+ status + ", noOfDays=" + noOfDays + "]";
	}
	@Id
    @GeneratedValue
    int sno;
    public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	@ManyToOne
    Employee employeeId;
    String LeaveType;


@Temporal(TemporalType.DATE)
    Date startdate;


@Temporal(TemporalType.DATE)
    Date enddate;
    @OneToOne(targetEntity=Employee.class)
    Employee applyTo;
    String reason;
    String status;
    int noOfDays;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public Employee getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}
	public String getLeaveType() {
		return LeaveType;
	}
	public void setLeaveType(String leaveType) {
		LeaveType = leaveType;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Employee getApplyTo() {
		return applyTo;
	}
	public  void setApplyTo(Employee applyTo) {
		this.applyTo = applyTo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public  void setStatus(String status) {
		this.status = status;
	}
}
