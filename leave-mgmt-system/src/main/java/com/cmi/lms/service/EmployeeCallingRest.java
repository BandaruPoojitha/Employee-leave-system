package com.cmi.lms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cmi.lms.RestURL;
import com.cmi.lms.beans.ApplyLeave;
import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Employee;

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

		ArrayList<ApplyLeave> al = arraylist.getBody();
		return al;
	}

	public ArrayList<ApplyLeave> cancelLeave(String employeeId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<ApplyLeave>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/cancel/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<ApplyLeave>>() {
				});

		ArrayList<ApplyLeave> al = arraylist.getBody();

		return al;
	}

	public ArrayList<ApplyLeave> trackLeave(String employeeId) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<ApplyLeave>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/leavedetails/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<ApplyLeave>>() {
				});

		ArrayList<ApplyLeave> al = arraylist.getBody();

		return al;
	}

	public ArrayList<BalanceLeaves> balanceLeaves(String employeeId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<BalanceLeaves>> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/balance/" + employeeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<BalanceLeaves>>() {
				});
		ArrayList<BalanceLeaves> bl = arraylist.getBody();

		return bl;
	}

	public Employee getManagerId(String empid) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee> arraylist = restTemplate.exchange(
				restURI.getURL() + "/employeerest/getmanagerId/" + empid, HttpMethod.GET, null,
				new ParameterizedTypeReference<Employee>() {
				});
		Employee bl = arraylist.getBody();
		return bl;
	}

	public String applyLeave(ApplyLeave al) {
		String uri1 = restURI.getURL() + "/employeerest/addleave";
		RestTemplate resttemplate = new RestTemplate();
		String result = resttemplate.postForObject(uri1, al, String.class);
		return result;
	}

	public String forwardLeave(ApplyLeave al) {
		String uri = restURI.getURL() + "/employeerest/forward";
		RestTemplate resttemplate = new RestTemplate();
		resttemplate.put(uri, al);
		return "updated";
	}

	public void updateStatus(ApplyLeave al) {
		RestTemplate resttemplate = new RestTemplate();
		String uri = restURI.getURL() + "/employeerest/status";
		resttemplate.put(uri, al);

	}

	public void cancel(int sno) throws Exception{
		RestTemplate resttemplate = new RestTemplate();
		String uri = restURI.getURL() + "/employeerest/cancelleave/" + sno;
		resttemplate.delete(uri);

	}

}
