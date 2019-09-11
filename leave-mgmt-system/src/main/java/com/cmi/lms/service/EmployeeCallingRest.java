package com.cmi.lms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.rest.RestURL;


@Component
public class EmployeeCallingRest {
	@Autowired
	RestURL restURI;
	public ArrayList<ApplyLeave> grantLeave(String employeeId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<ApplyLeave>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/grant/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<ApplyLeave>>() {
				});

		ArrayList<ApplyLeave> al=arraylist.getBody();
		return al;
	}
	public ArrayList<ApplyLeave> cancelLeave(String employeeId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<ApplyLeave>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/cancel/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<ApplyLeave>>() {
				});

		ArrayList<ApplyLeave> al=arraylist.getBody();
//		String uri = restURI.getURL()+"/employeerest/cancel";
//		ArrayList<String> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		return al;
	}
	public ArrayList<ApplyLeave> trackLeave(String employeeId) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<ApplyLeave>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/leavedetails/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<ApplyLeave>>() {
				});

		ArrayList<ApplyLeave> al=arraylist.getBody();

		return al;
	}
	public ArrayList<BalanceLeaves> balanceLeaves(String employeeId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<BalanceLeaves>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/balance/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<BalanceLeaves>>() {
				});
		ArrayList<BalanceLeaves> bl=arraylist.getBody();
//		String uri = restURI.getURL()+"/employeerest/balance";
//		ArrayList<Integer> arraylist = restTemplate.getForObject(uri, ArrayList.class);
		return bl;
	}
	public String getManagerId(String empid) {
		String uri = restURI.getURL()+"/employeerest/getmanagerId/"+empid;
		RestTemplate rt = new RestTemplate();
		Employee employee = rt.getForObject(uri, Employee.class);
		return employee.getManagerId();
	}
	public String applyLeave(ApplyLeave al,String empid) {
		String uri1 =restURI.getURL()+"/employeerest/addleave/"+empid;
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
