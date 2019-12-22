package com.mohit2906.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService
{
	
	@Autowired
	private UserProfileJpaRepository userRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		System.out.println("___ UserName ____ " + username);
		
		UserProfile userProfile = userRepo.findByUsername(username);
		
		System.out.println("___ User Profile ___ " + userProfile);
		
		if(userProfile == null) throw new UsernameNotFoundException("User 404");
		
		return new org.springframework.security.core.userdetails.User(userProfile.getUsername(), userProfile.getPassword(),
				new ArrayList<>());
		
	}

}
