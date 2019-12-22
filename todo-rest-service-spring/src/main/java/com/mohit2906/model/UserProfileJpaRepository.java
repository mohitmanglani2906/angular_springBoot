package com.mohit2906.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileJpaRepository extends JpaRepository<UserProfile, Long>{
		
	List<UserProfile> findByEmail(String email);
	
	UserProfile findByUsername(String username);
	
	
}
