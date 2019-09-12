package com.cmi.lms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestURL {
	@Value("${RestUrl}")
	private String resturl;
	public String getURL() {
		
		return resturl;
		
	}
}
