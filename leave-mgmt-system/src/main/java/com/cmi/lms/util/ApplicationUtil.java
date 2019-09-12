package com.cmi.lms.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cmi.lms.beans.ApplyLeave;

public class ApplicationUtil {
	public long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}
	public boolean emailValidation(String email) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9]+\\.*[a-zA-Z0-9]+)@([a-zA-Z0-9]+)\\.[a-zA-Z0-9]{3}");
		Matcher matcher;
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	boolean passwordValidation(String password) {
		Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])).{8,}");

		Matcher matcher;
		matcher = pattern.matcher(password);

		return matcher.matches();
	}

	public boolean validatephonenumber(String phno) {
		Pattern pattern = Pattern.compile("\\d{10}");

		Matcher matcher;
		matcher = pattern.matcher(phno);

		return matcher.matches();
	}
	//checks leavedetails date with currentdate.if enddate is after currentdate then only Employee can cancel.
	public ArrayList<ApplyLeave> cancelLeave(ArrayList<ApplyLeave> resultList) {
		ArrayList<ApplyLeave>  al=new ArrayList<>();
	LocalDate currentdate=LocalDate.now();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	for(int i=0;i<resultList.size();i++) {
	String strDate = dateFormat.format(resultList.get(i).getEnddate());   
	LocalDate enddate=LocalDate.parse(strDate);
	if(enddate.isAfter(currentdate)){
		al.add(resultList.get(i));
	}

	}
	return al;
	}
}
