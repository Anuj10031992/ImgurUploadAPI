package com.example.synchrony.assignment.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.synchrony.assignment.demo.model.userProfile;
@Repository
public interface UserRepository extends JpaRepository<userProfile, String> {

	
	 Optional<userProfile> findByLoginId(String loginId);
	 Optional<userProfile> findByEmail(String email);
}
