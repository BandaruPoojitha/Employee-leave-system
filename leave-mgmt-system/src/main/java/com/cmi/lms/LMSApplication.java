package com.cmi.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.cmi.lms", "com.cmi.lms.service", "com.cmi.lms.bussiness" , "com.cmi.lms.rest", "com.cmi.lms.controller"})
@EntityScan(basePackages = { "com.cmi.lms.beans" })
@EnableJpaRepositories(basePackages = { "com.cmi.lms.repository" })
public class LMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(LMSApplication.class, args);
	}

}
