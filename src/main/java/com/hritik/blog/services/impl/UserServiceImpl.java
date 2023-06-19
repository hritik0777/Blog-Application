package com.hritik.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hritik.blog.entities.User;
import com.hritik.blog.exceptions.ResourceNotFoundException;
import com.hritik.blog.payloads.UserDto;
import com.hritik.blog.repositories.UserRepo;
import com.hritik.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		
		User user = DtoToEntity(userdto);
		
		User saveduser =  this.userRepo.save(user);
		
		return EntityToDto(saveduser);	
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) {
		// TODO Auto-generated method stub
		
		
		User user = this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , userid)); 
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		
		user.setPassword(userDto.getPassword());
		
		user.setAbout(userDto.getAbout());
		
		User saveduser = this.userRepo.save(user);
		
		UserDto userDto1 = EntityToDto(saveduser);
		
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userid) {
		// TODO Auto-generated method stub
		
		
	User user	= this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userid));
		
		return EntityToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = userRepo.findAll();
		
		List<UserDto> userdtos = users.stream().map(user->this.EntityToDto(user)).collect(Collectors.toList());
		
		
		return userdtos;
		 
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(Integer userid) {
		// TODO Auto-generated method stub
		
		
		User user= this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userid));
        
		this.userRepo.delete(user);
		 
		
	}
	
	private User DtoToEntity(UserDto userdto) {
		
		User user = modelMapper.map(userdto, User.class);
		
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		
//		user.setPassword(userdto.getPassword());
//		
//		user.setAbout(userdto.getAbout());
		
		
		
		return user;
	}
	
	
	
	private UserDto EntityToDto(User user) {
		
		
		UserDto userdto = modelMapper.map(user, UserDto.class);
		
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		
//		userdto.setPassword(user.getPassword());
//		
//		userdto.setAbout(user.getAbout());
		
		
		return userdto;
		
		
	}

}
