package com.mdtalalwasim.blog.app.payloads;

import java.util.HashSet;
import java.util.Set;

import com.mdtalalwasim.blog.app.entity.Role;

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
	
	
	//User Dto consist only those fields which are actually taken from user input.
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username atleast 4 character long!")
	private String name;
	
	@Email(message = "Invalid Email address, Please Input valid Email!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 6, message = "Input password within 3 to 6 Characters")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
