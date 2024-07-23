package com.stackroute.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.stackroute.model.UserService;

public interface UserServiceService {

	UserService registerUser(UserService user);
	UserService getUserByEmail(String email);
	UserService updateUser(UserService user, String userEmail);
	String uploadProfilepic(String userEmail, MultipartFile file);
	UserService updatePlan(UserService user, String userEmail);
	
	
}
