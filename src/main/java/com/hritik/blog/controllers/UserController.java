package com.hritik.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hritik.blog.payloads.ApiResponse;
import com.hritik.blog.payloads.UserDto;
import com.hritik.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Create User Method;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		
		
		 UserDto createUserDto =  this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);
		
	}
	
	
	// Update User 
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable("userId") Integer u_id ){
		
		
		UserDto upadtedUserDto = this.userService.updateUser(userDto, u_id) ;
		
		return ResponseEntity.ok(upadtedUserDto);
	}
	
	
	//Delete a User 
	
	
	
     @DeleteMapping("/{user_id}")
     public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") Integer u_id){
    	 
    	 this.userService.deleteUser(u_id);
    	 
    	 return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted", true),HttpStatus.OK);
    	 
     }
     
     //Get All Users 
     
     @GetMapping("/")
     public ResponseEntity<List<UserDto>> getAllUsers()
     {
    	 return ResponseEntity.ok(this.userService.getAllUsers());
     }
     
     //Get Single User by Id
     
     @GetMapping("/{user_id}")
     public ResponseEntity<UserDto> getUser(@PathVariable Integer user_id)
     {
    	 return ResponseEntity.ok(this.userService.getUserById(user_id)); 
     }

}
