package com.example.synchrony.assignment.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.synchrony.assignment.demo.model.userImages;

public interface ImageRepository extends JpaRepository<userImages, Integer> {

	List<userImages> findByUserLoginId(String loginId);
//	List<userImages> findByUserProfileName(String name);
	Optional<userImages> findByUserLoginIdAndImageName(String loginId,String name);
	Optional<userImages> findByUserLoginIdAndLink(String loginId,String link);
	Optional<userImages> findByUserLoginIdAndId(String loginId,String id);

}