package com.mohit2906.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@Autowired
//	RestTemplate restTemplate;
	
	@PostMapping("/db/users")
	public ResponseEntity<Void> createTodo(
			@RequestBody UserProfile userProfile){
		
		System.out.println("____ user profile ____  " + userProfile);
		
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
		

//		String statusCode = sendEmail.sendEmailToUsers(userProfile.getEmail()); // Send Welcome Email
//		

//		if(!statusCode.equals("Success")){
//			return ResponseEntity.status(400).build();
//		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("/db/users/all/{username}")
	public UserProfile getUsers(@PathVariable String username) {
		
		//return userProfileJpaRepo.findAll();	
		return userProfileJpaRepo.findByUsername(username);
		
	}
	
	@GetMapping("/db/users/all")
	public List<UserProfile> getAllUsers()
	{
		return userProfileJpaRepo.findAll();
	}
	
	@GetMapping("/db/users/all/matching/{username}")
	public List<UserProfile> findByCriteria(@PathVariable String username, @PageableDefault(size=2, sort = "id")
			Pageable pageable){
		Page page =  userProfileJpaRepo.findAll(new Specification<UserProfile>() {
			
			@Override
			public Predicate toPredicate(Root<UserProfile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				if(username != null) {
//					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("username"),username)));
					
					 //predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("username"), username)));
					
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("username"),"%" + username + "%")));
				}
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		},pageable);
		
		page.getTotalElements();
		page.getTotalPages();
		
		System.out.println("___ Total Elements and Total pages ___ " + page.getTotalElements() + " "  + page.getTotalPages());
		
		return page.getContent();
		
		//return userPro; 
	}
	
	
//	@PostMapping("/test1")
//	public String test1(@RequestBody UserProfile userProfile) {
//		HttpHeaders headers = new HttpHeaders();
//	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<UserProfile> entity = new HttpEntity<UserProfile>(userProfile);
//		
//		return restTemplate.exchange("https://localhost:8080/test2",HttpMethod.POST,entity,String.class).getBody();
//	}
//	
//	@PostMapping("/test2")
//	public String test2() {
//		System.out.println("___ APi Called ___");
//		return "Done";
//	}
	
}
