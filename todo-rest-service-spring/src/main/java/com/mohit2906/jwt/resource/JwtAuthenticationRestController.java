package com.mohit2906.jwt.resource;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mohit2906.jwt.JwtTokenUtil;
//import com.mohit2906.jwt.JwtUserDetails;
import com.mohit2906.model.JwtUserDetailsService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationRestController {
	
  private final Logger logger = LoggerFactory.getLogger(this.getClass());	

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired	
  private JwtTokenUtil jwtTokenUtil;

//  @Autowired
//  private UserDetailsService jwtInMemoryUserDetailsService;
//  
  @Autowired
  private JwtUserDetailsService userDetails;
  
  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {
	  
	 
	System.out.println("____ UserName and Password ____ " + 
    		authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());
	
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    
    final UserDetails userDetails = this.userDetails.loadUserByUsername(authenticationRequest.getUsername());
    
    logger.info("___ User Details ___", userDetails);
    
    final String token = jwtTokenUtil.generateToken(userDetails);
    
    logger.info("____ Token ___",token);

    return ResponseEntity.ok(new JwtTokenResponse(token));
  }

  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
//    JwtUserDetails user = (JwtUserDetails) this.userDetails.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  private void authenticate(String username, String password) {
//    Objects.requireNonNull(username);
//    Objects.requireNonNull(password);
//    
    System.out.println("___ In authenticate function ____ " );

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      
      System.out.println("___ In authenticate function ____ " );
      
    } catch (DisabledException e) {
    	
      System.out.printf("USER_DISABLED", e.getMessage());	
      throw new AuthenticationException("USER_DISABLED", e);
      
    } catch (BadCredentialsException e) {
      System.out.printf("INVALID_CREDENTIALS", e.getMessage());	
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
//    catch(Exception e) {
//    	System.out.println(e.getMessage() + " Exception ");
//    }
  }
}

