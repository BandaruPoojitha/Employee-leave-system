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

import com.cmi.lms.beans.Department;
import com.cmi.lms.beans.Employee;
import com.cmi.lms.beans.Login;
import com.cmi.lms.rest.RestURL;
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

		String uri = restURI.getURL() + "/adminrest/adddepartment/" + department.getDepartmentId() + "/"
				+ department.getManagerId();
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> viewDepartment() {

		String uri = restURI.getURL() + "/adminrest/viewdepartment";
		RestTemplate restTemplate = new RestTemplate();
		

		ArrayList<String> result = restTemplate.getForObject(uri, ArrayList.class);
		return result;
	}

	public void editAddress(Employee employee) {
		String uri = restURI.getURL() + "/adminrest/editaddress/" + employee.getAddress() + "/"
				+ employee.getEmployeeId();
		RestTemplate rt = new RestTemplate();
		rt.getForObject(uri, String.class);

	}

	public void editEmail(Employee employee) {
		String uri = restURI.getURL() + "/adminrest/editemail/" + employee.getEmail() + "/" + employee.getEmployeeId();
		RestTemplate rt = new RestTemplate();
		rt.getForObject(uri, String.class);

	}

	public void editPhoneNumber(Employee employee) {
		String uri = restURI.getURL() + "/adminrest/editcontact/" + employee.getPhonenumber() + "/"
				+ employee.getEmployeeId();
		RestTemplate rt = new RestTemplate();
		rt.getForObject(uri, String.class);

	}

	public ArrayList<Login> getEmployeeId() {
		// String uri =restURI.getURL()+"/adminrest/getempid" ;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<Login>> arraylist = restTemplate.exchange(restURI.getURL() + "/adminrest/getempid",
				HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Login>>() {
				});
		ArrayList<Login> al = arraylist.getBody();

		return al;
	}
}
