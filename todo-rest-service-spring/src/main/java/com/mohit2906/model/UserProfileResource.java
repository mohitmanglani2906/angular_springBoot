package com.mohit2906.model;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.mohit2906.utils.SendEmail;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserProfileResource 
{
	@Autowired
	UserProfileJpaRepository userProfileJpaRepo;
	
	@Autowired
	SendEmail sendEmail;
	
	@PostMapping("/db/users")
	public ResponseEntity<Void> createTodo(
			@RequestBody UserProfile userProfile){
		
		System.out.println("____ user profile ___  " + userProfile);
		
		UserProfile createdUser = null;
		
		try {
			userProfile.setPassword(new BCryptPasswordEncoder().encode(userProfile.getPassword()));
			
//			List<UserProfile> uList = userProfileJpaRepo.findByEmail(userProfile.getEmail());
//			System.out.println("____ User List _____ " + uList);
			createdUser = userProfileJpaRepo.save(userProfile);
		}
		
		catch(Exception e) {
			System.out.println("____ Error ____" + e.getMessage());
			return ResponseEntity.status(400).build();		}
		
		String statusCode = sendEmail.sendEmailToUsers(userProfile.getEmail()); // Send Welcome Email
		
		if(!statusCode.equals("Success")){
			return ResponseEntity.status(400).build();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
}
