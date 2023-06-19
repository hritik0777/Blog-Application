package com.hritik.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message = "Name should be of minimum 4 characters")
	private String name;
	
	
	@Email(message="Email must be wrong !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password should not exceed 10 chars and minimum should have 3 chars")
	private String password;
	
	@NotEmpty
	private String about;
	

}
