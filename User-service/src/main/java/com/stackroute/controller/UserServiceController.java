package com.stackroute.controller;

//import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.stackroute.exception.ImageUploadException;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.model.UserLoginData;
import com.stackroute.model.UserService;
import com.stackroute.service.UserServiceService;

@RestController
@RequestMapping("/userservice")
public class UserServiceController {

	@Autowired
	private UserServiceService userService;
	
	
	@Autowired
	private Cloudinary cloudinary;


	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@GetMapping("/home")
	public String home() {
		return "testing the output";
	}
	
	
	
	
	
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserService user){
		ResponseEntity<?> entity;
		try {
			UserService createdUser=userService.registerUser(user);
			if(createdUser!=null) {
				UserLoginData login= new UserLoginData();
				login.setUserEmail(user.getUserEmail());
				login.setUserPasswordHash(user.getUserPasswordHash());
				
				String otherServiceUrl="http://localhost:8002/auth/addNewUser";
				entity=new ResponseEntity<UserService>(createdUser, HttpStatus.OK);
			}else {
				entity=new ResponseEntity<String>("Cant be added", HttpStatus.CONFLICT);
			}
			
		}catch(UserAlreadyExistsException e) {
			entity = new ResponseEntity<String>("Same user with mail found"+e.getMessage(), HttpStatus.CONFLICT);
		}catch(Exception e) {
			entity= new ResponseEntity<String>("Error thrown "+e.getMessage(),HttpStatus.CONFLICT);
		}
		return entity;
		
	}
	
	
	@PutMapping("/updateUser/{userEmail}")
	public ResponseEntity<?> updateUser(@PathVariable String userEmail, @RequestBody UserService user){
		ResponseEntity<?> entity;
		
		try {
			UserService updatedUser= userService.updateUser(user, userEmail);
			
			if(updatedUser!=null) {
				entity= new ResponseEntity<UserService>(updatedUser, HttpStatus.OK);
			}else {
				entity= new ResponseEntity<String>("Failed to update", HttpStatus.CONFLICT);
			}
		}catch(Exception e){
			entity= new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
		}
		return entity;
		
	}
	
	
	
	@PutMapping("/updatePlan/{userEmail}")
	public ResponseEntity<?> updatePlan(@PathVariable String userEmail, @RequestBody UserService user){
		ResponseEntity<?> entity;
		
		try {
			UserService updatedPlan= userService.updatePlan(user, userEmail);
			
			if(updatedPlan!=null) {
				entity= new ResponseEntity<UserService>(updatedPlan, HttpStatus.OK);
			}else {
				entity= new ResponseEntity<String>("Failed to update", HttpStatus.CONFLICT);
			}
		}catch(Exception e){
			entity= new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
		}
		return entity;
		
	}
	
	
	@GetMapping("/userByEmail/{userEmail}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail){
		ResponseEntity<?> entity;
		
		try {
			UserService user= userService.getUserByEmail(userEmail);
			
			if(user!=null) {
				entity= new ResponseEntity<UserService>(user, HttpStatus.OK);
			}else {
				entity = new ResponseEntity<String>("User with email not found", HttpStatus.CONFLICT);
			}
		}catch(Exception e) {
			entity= new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
		}
		return entity;
	}
	
	@PostMapping("/updateProfilePic/{userEmail}")
	public ResponseEntity<String> uploadProfilePic(@PathVariable String userEmail ,@RequestParam("file") MultipartFile file){
		ResponseEntity<?> entity;
		
		try {
			String url=userService.uploadProfilepic(userEmail, file);
			return new ResponseEntity<>(url, HttpStatus.OK);
		}catch (ImageUploadException e) {
			return new ResponseEntity<String>("Error loading image",HttpStatus.CONFLICT);
		}
		
		
	}
	
	
}
