package com.mohit2906.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserProfileJpaRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile>{
		
	List<UserProfile> findByEmail(String email);

	UserProfile findByUsername(String username);

}
