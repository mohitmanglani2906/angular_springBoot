package com.mohit2906.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

	
@CrossOrigin(origins="http://localhost:4200",exposedHeaders="Access-Control-Allow-Origin")
@RestController
public class BasicAuthenticationController {
	
	@GetMapping("/basicauth")
	public AuthenticationBean getBean() { 
		return new AuthenticationBean("You are authenticated");
	}
	

}
