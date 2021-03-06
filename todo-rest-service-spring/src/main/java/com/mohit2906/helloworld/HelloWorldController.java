package com.mohit2906.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200",exposedHeaders="Access-Control-Allow-Origin")
@RestController
//@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
public class HelloWorldController {
	
	
	
	@GetMapping("/hello-world")
	public String getHello() {
		return "Hello Wolrd";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean getBean() {
		return new HelloWorldBean("Hello-World-Mohit");
	}
	
	@GetMapping("/hello-world-bean/path/{name}")
	public HelloWorldBean getPathvariable(@PathVariable String name) 
	{	
		try {
			System.out.println("___ Hello Wolrd ___");
			return new HelloWorldBean("Hello World " + name);
		}
		
		catch(Exception e) {
			System.out.println("__ Error ___" + e.getMessage());
		}
		
		return new HelloWorldBean("Error");
		
		
		
	}
}
