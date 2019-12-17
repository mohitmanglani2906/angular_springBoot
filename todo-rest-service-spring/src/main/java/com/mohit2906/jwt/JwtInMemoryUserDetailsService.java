package com.mohit2906.jwt;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
	
  private final Logger logger = LoggerFactory.getLogger(this.getClass());	
	
  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "Mohit",
    		"$2a$10$goLxzfiYlcinuoFV.2iOBesEeN9kljGK8gSki/rnYAdkDNus27/Tq", "ROLE_USER_3"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
       .filter(user -> user.getUsername().equals(username)).findFirst();
	  
//	 JwtUserDetails findFirst = inMemoryUserList.stream()
//		        .filter(user -> user.getUsername().equals(username)).findFirst().get();
    
    logger.info("____ Find First ____ " + findFirst.get());

//    if (!findFirst.isPresent()) {
//      logger.info("___ User Not Found ___ ");	
//      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//    }

    return findFirst.get();
  }

}


