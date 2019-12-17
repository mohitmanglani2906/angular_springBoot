package com.mohit2906;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		
		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		 
		 for(int i=0;i<=10;i++) {
			 String encodedString =  bCryptPasswordEncoder.encode("password@123");
			 System.out.println(encodedString);

		 }
		 
		 
	}

}
