package com.cmi.lms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cmi.lms.RestURL;
import com.cmi.lms.beans.Department;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.beans.Login;
@Component
@Service
@EnableAutoConfiguration
public class AdminCallingRest {
	@Autowired
	RestURL restURI;

	public String addemployee(Employee employee) {

		String uri = restURI.getURL() + "/adminrest/createemployee";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(uri, employee, String.class);
		return result;
	}

	public String addDepartment(Department department) {

		String uri = restURI.getURL() + "/adminrest/adddepartment";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(uri, department,String.class);
		return result;

	}

	public ArrayList<Department> viewDepartment() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<Department>> arraylist = restTemplate.exchange(restURI.getURL() + "/adminrest/viewdepartment",
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Department>>() {
				});
		ArrayList<Department> al = arraylist.getBody();
		return al;
	}

	public void editAddress(Employee employee) {
		String uri = restURI.getURL() + "/adminrest/editaddress";
		RestTemplate rt = new RestTemplate();
		rt.put(uri, employee);

	}

	public void editEmail(Employee employee) {
		String uri = restURI.getURL() + "/adminrest/editemail";
		RestTemplate rt = new RestTemplate();
		rt.put(uri, employee);

	}

	public void editPhoneNumber(Employee employee) {
		String uri = restURI.getURL() + "/adminrest/editcontact";
		RestTemplate rt = new RestTemplate();
		rt.put(uri, employee);

	}

	public ArrayList<Login> getEmployeeId(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<Login>> arraylist = restTemplate.exchange(restURI.getURL() + "/adminrest/getempid",
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Login>>() {
				});
		ArrayList<Login> al = arraylist.getBody();

		return al;
	}
}
