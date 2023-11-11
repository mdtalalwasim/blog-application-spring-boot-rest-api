package com.mdtalalwasim.blog.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.blog.app.exceptions.*;
import com.mdtalalwasim.blog.app.config.ApplicationConstants;
import com.mdtalalwasim.blog.app.entity.Role;
import com.mdtalalwasim.blog.app.entity.User;
import com.mdtalalwasim.blog.app.payloads.UserDto;
import com.mdtalalwasim.blog.app.repository.RoleRepository;
import com.mdtalalwasim.blog.app.repository.UserRepository;
import com.mdtalalwasim.blog.app.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser =this.userRepository.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		//method-1
		Optional<User> user = this.userRepository.findById(userId);
		
		if(user.isPresent()) {
			User userObj = new User();
			System.out.println("Inside userIsPresent");
			
			userObj.setId(userId);
			userObj.setName(userDto.getName());
			userObj.setEmail(userDto.getEmail());
			userObj.setPassword(userDto.getPassword());
			userObj.setAbout(userDto.getAbout());
			
			User updateUser = this.userRepository.save(userObj);
			//convert with respect to our return type
			UserDto userDtoObj = this.userToDto(updateUser);
			
			return userDtoObj;
		}else {
			throw new ResourceNotFoundException("User"," Id ",userId);

		}
		
		//method-2
		
//		User user = this.userRepository.findById(userId)
//				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
//		
//		//set
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
//		//update
//		User updateUser = this.userRepository.save(user);
//		//convert with respect to our return type
//		UserDto userDtoObj = this.userToDto(updateUser);
//		
//		return userDtoObj;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> usersList = this.userRepository.findAll(); //list of users
		
		//convert list of Users to list of UserDto.
		List<UserDto> userDtoList = usersList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		this.userRepository.delete(user);
		
	}
	
	
	//Converted one Model Object to Another: 	Way-1
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		//encoded password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//set the role to new user 
		//ApplicationConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE value = '7002' --- this.roleRepository.findById(7002);
		Role role = this.roleRepository.findById(ApplicationConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE).get();
		
		//set this role to this user
		user.getRoles().add(role);
		
		//now save new User
		//now save new User
		User newUser = this.userRepository.save(user);
		
		
		
		
		return this.modelMapper.map(newUser, UserDto.class);
	}
	
	//Alternative way to convert Model Object to Another: Way-2
	//Manually Map two objects / map two models / Transfer one Model data to another model.
//	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
//		return user;
//	}
	
	//Manually Map two objects / map two models / Transfer one Model data to another model.
//	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		
//		return userDto;
//	}

}
