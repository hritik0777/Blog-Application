package com.hritik.blog.services;

import java.util.List;

import com.hritik.blog.payloads.UserDto;

public interface UserService {

	
	UserDto createUser(UserDto user);
	UserDto updateUser( UserDto user, Integer userid);
	UserDto getUserById(Integer userid);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userid);
	
	
	
}
