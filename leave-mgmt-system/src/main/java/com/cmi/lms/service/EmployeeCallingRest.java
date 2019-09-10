package com.cmi.lms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.rest.RestURL;


@Component
public class EmployeeCallingRest {
	@Autowired
	RestURL restURI;
	public ArrayList<String> grantLeave() {
		String uri =restURI.getURL()+"/employeerest/grant";
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<String> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		return arraylist;
	}
	public ArrayList<String> cancelLeave() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = restURI.getURL()+"/employeerest/cancel";
		ArrayList<String> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		return arraylist;
	}
	public ArrayList<String> trackLeave() {
		
		RestTemplate restTemplate = new RestTemplate();
		String uri =restURI.getURL()+"/employeerest/leavedetails";
		ArrayList<String> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		return arraylist;
	}
	public ArrayList<Integer> balanceLeaves() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = restURI.getURL()+"/employeerest/balance";
		ArrayList<Integer> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		return arraylist;
	}
	public String getManagerId() {
		String uri = restURI.getURL()+"/employeerest/getmanagerId";
		RestTemplate rt = new RestTemplate();
		String managerId = rt.getForObject(uri, String.class);
		return managerId;
	}
	public String applyLeave(ApplyLeave al) {
		String uri1 =restURI.getURL()+"/employeerest/addleave";
		RestTemplate resttemplate = new RestTemplate();
		String result = resttemplate.postForObject(uri1, al, String.class);
		return result;
	}
	public String forwardLeave(int sno, String managerId) {
		String uri = restURI.getURL()+"/employeerest/forward/" + sno + "/" + managerId;
		RestTemplate resttemplate = new RestTemplate();
		String result = resttemplate.getForObject(uri, String.class);
		return result;
	}
	public void updateStatus(int sno, String value) {
		RestTemplate resttemplate = new RestTemplate();
		String uri =restURI.getURL()+"/employeerest/status/" + sno + "/" + value;
	resttemplate.getForObject(uri, String.class);
	
	}
	public void cancel(int sno) {
		RestTemplate resttemplate = new RestTemplate();
		String uri = restURI.getURL()+"/employeerest/cancelleave/" + sno;
		 resttemplate.getForObject(uri, String.class);
		
	}

}
