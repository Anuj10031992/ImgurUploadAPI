package com.example.synchrony.assignment.demo.conroller;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.synchrony.assignment.demo.model.userProfile;
import com.example.synchrony.assignment.demo.service.userService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController("/")
public class UserConroller {

	
	private static final Logger logger = LoggerFactory.getLogger(UserConroller.class);
	
	@Autowired
	private userService usrvc;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder; 
	
	 
	 
	 @GetMapping("user")
	 public List<userProfile> getUsers()
	 {
		return usrvc.getUsers();
	 }
	 @GetMapping("user/{loginid}")
	 public ResponseEntity<userProfile> getUser(@PathVariable("loginid") String loginid)
	 {
		 userProfile userprofile=usrvc.getUser(loginid);
		if(userprofile!=null)
		{
			return ResponseEntity.status(HttpStatus.FOUND).body(userprofile);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new userProfile());
		}
		
	 }
	@PostMapping("user")
	public ResponseEntity<String> adduser(@RequestBody @Valid userProfile user)
	{
		
		user.setPassword(passwordEncoder.encode(user.getPassword())); //encoding user password
		int result=usrvc.addUser(user);
		String text="";	
		if(result==1 || result==2)
		{
		
		if(result==1)
		{
			text="Login Id Already Used";
		}
		else
		{
			text="email Id already present";
		}
		 
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(text);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(text);
	}
}
