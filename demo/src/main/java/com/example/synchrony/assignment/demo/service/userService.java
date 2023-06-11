package com.example.synchrony.assignment.demo.service;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.synchrony.assignment.demo.model.userProfile;
import com.example.synchrony.assignment.demo.repository.UserRepository;

@Service
public class userService {

	@Autowired
	private UserRepository userrepo;
	
	
	
	public int addUser(userProfile user)
	{
		Optional<userProfile> user1=userrepo.findByLoginId(user.getLoginId());
		if(user1.isPresent())
		{
			return 1;//login id already used.
		}
		Optional<userProfile> user2=userrepo.findByEmail(user.getEmail());
		if(user2.isPresent())
		{
			return 2;//email id already used.
		}
		userrepo.save(user);
		return 0;
	}



	public List<userProfile> getUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}



	public userProfile getUser(String loginid) {
		// TODO Auto-generated method stub
		Optional<userProfile> optn=userrepo.findByLoginId(loginid);
		if(optn.isPresent())
		{
			return optn.get();
		}
		else
		{
			return null;
		}
	}
	
}
