package com.cmi.lms.bussiness;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.cmi.lms.beans.ApplyLeave;




public class CancelLeaveValiation {
	//checks leavedetails date with currentdate.if enddate is after currentdate then only Employee can cancel.
	public boolean cancelLeave(ArrayList<ApplyLeave> resultList,int i) {

	LocalDate currentdate=LocalDate.now();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	
	String strDate = dateFormat.format(resultList.get(i).getEnddate());   
	LocalDate enddate=LocalDate.parse(strDate);
	if(enddate.isAfter(currentdate)){
		return true;
	}
	else
	{
		return false;
	}
	}
}

