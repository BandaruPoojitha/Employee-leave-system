package com.cmi.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cmi.lms.RestURL;
import com.cmi.lms.beans.Login;


@Component
public class LoginCallRest {
	@Autowired
	RestURL restURI;
	public Login getLoginDetails(Login login) throws Exception {
	
		String uri = restURI.getURL()+"/lms/login";
		RestTemplate restTemplate = new RestTemplate();
		Login login1=restTemplate.postForObject(uri, login, Login.class);

		return login1;
	}

}
