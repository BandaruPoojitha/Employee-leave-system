package com.cmi.lms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.lms.beans.Login;
import com.cmi.lms.service.LoginServiceRepo;

@RestController
@RequestMapping("/lms")
public class LoginRestController {
	
	@Autowired
	LoginServiceRepo loginservice;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Login login(@RequestBody Login loginReq) throws Exception {
		
		return loginservice.getDetails(loginReq);

	}
}
