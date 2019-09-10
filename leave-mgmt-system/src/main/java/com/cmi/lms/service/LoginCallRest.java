package com.cmi.lms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cmi.lms.beans.Login;
import com.cmi.lms.rest.RestURL;


@Component
public class LoginCallRest {
	@Autowired
	RestURL restURI;
	public Login getLoginDetails(Login login) {
	
		String uri = restURI.getURL()+"/lms/login";
		RestTemplate restTemplate = new RestTemplate();
		ModelAndView modelAndView = new ModelAndView("/Login");
		Login login1=restTemplate.postForObject(uri, login, Login.class);

		return login1;
	}

}
