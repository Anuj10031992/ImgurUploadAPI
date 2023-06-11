package com.example.synchrony.assignment.demo.comp;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.synchrony.assignment.demo.model.userProfile;
import com.example.synchrony.assignment.demo.repository.UserRepository;


@Component
public class AuthProvider implements AuthenticationProvider  {


	   @Autowired 
	   private PasswordEncoder passwordEncoder;


		@Autowired
		private UserRepository userrepo;
	   
	   public Authentication authenticate(Authentication authentication) 
	   throws AuthenticationException {
		   //System.out.println("WE are checking");
	      String username = authentication.getName();
	      String password = (String)authentication.getCredentials();
	      System.out.println("User Name="+username);
	      

	      System.out.println("userRepository" +userrepo);
	      System.out.println("passwordEncoder" +passwordEncoder);
	      if(userrepo==null)
	    	  return null;
	     // System.out.println(passwordEncoder.encode("dummy@12345"));
	   userProfile loginuser=userrepo.findByLoginId(username).get();
	   
	      if(passwordEncoder.matches(password, loginuser.getPassword()))
	      {
	    	 
	            return new UsernamePasswordAuthenticationToken(username, password, null);

	      }
	      
	      
	      
	      return null;
	   } 
	   @Override 
	   public boolean supports(Class<?> authentication) { 
	      return true; 
	     // return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	   }
	
}
