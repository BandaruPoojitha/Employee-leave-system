package com.cmi.lms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmi.lms.beans.Login;
import com.cmi.lms.repository.LoginRepo;

@Service
public class LoginServiceRepo {
	@Autowired
	LoginRepo loginrepo;

	public Login getDetails(Login l) {

		String username = l.getUsername();

		Optional<Login> newl = loginrepo.findById(username);
       
		return newl.get();

	}
}