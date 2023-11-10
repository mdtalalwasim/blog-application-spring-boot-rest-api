package com.mdtalalwasim.blog.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdtalalwasim.blog.app.payloads.ApiResponse;
import com.mdtalalwasim.blog.app.payloads.UserDto;
import com.mdtalalwasim.blog.app.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	
	//create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	
	//update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		//return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);//or
		return  ResponseEntity.ok(updatedUser);
	}
	
	//to make access for only ADMIN ROLE.  
	//Admin can delete only
	// delete user
	@PreAuthorize("hasRole('ADMIN')")//by adding this, this 'deleteUser' method can only use by 'ADMIN' only admin has the authority to delete this. 
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id) {

		this.userService.deleteUser(id);
		// return ResponseEntity.ok("User Deleted Successfully.");//or
		// return new ResponseEntity(Map.of("message","User Deleted
		// Successfully."),HttpStatus.OK);//or
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully.", true), HttpStatus.OK);// or

	}
	
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsersList = this.userService.getAllUsers();
		
		//return new ResponseEntity<List<UserDto>>(allUsersList,HttpStatus.OK);//or
		return ResponseEntity.ok(allUsersList);//or
	}
	
	//get single user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		UserDto user = this.userService.getUserById(userId);
		//return new ResponseEntity<List<UserDto>>(allUsersList,HttpStatus.OK);//or
		return ResponseEntity.ok(user);//or
	}
	

	

}
