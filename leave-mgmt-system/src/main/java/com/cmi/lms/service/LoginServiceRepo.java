package com.cmi.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.lms.beans.Login;
import com.cmi.lms.exception.LMSUnAuthorisedException;
import com.cmi.lms.repository.LoginRepo;

@Service
public class LoginServiceRepo {
	@Autowired
	LoginRepo loginrepo;

	public Login getDetails(Login l) {

		Login newl = loginrepo.login(l.getUsername(), l.getPassword());

		if (newl == null) {
			throw new LMSUnAuthorisedException("Invalid Credentials");
		}
		return newl;

	}
}