package com.cmi.lms.bussiness;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmployee {
	public boolean emailValidation(String email) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9]+\\.*[a-zA-Z0-9]+)@([a-zA-Z0-9]+)\\.[a-zA-Z0-9]{3}");
		Matcher matcher;
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	boolean passwordValidation(String password) {
		Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])).{8,}");

		Matcher matcher;
		matcher = pattern.matcher(password);

		return matcher.matches();
	}

	public boolean validatephonenumber(String phno) {
		Pattern pattern = Pattern.compile("\\d{10}");

		Matcher matcher;
		matcher = pattern.matcher(phno);

		return matcher.matches();
	}
}
