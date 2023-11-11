package com.mdtalalwasim.blog.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mdtalalwasim.blog.app.config.ApplicationConstants;
import com.mdtalalwasim.blog.app.entity.Role;
import com.mdtalalwasim.blog.app.repository.RoleRepository;

@SpringBootApplication
public class BlogApplicationSpringbootRestApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//in this project role will not create automatically till now, now we will create role table automatically when this project runs.
	
	@Autowired 
	private RoleRepository roleRepository;
	
	
	//this will add
	public BlogApplicationSpringbootRestApisApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationSpringbootRestApisApplication.class, args);
		System.out.println("Hello");
	}	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	//CommandLineRunner method
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("123456"));
		System.out.println(this.passwordEncoder.encode("selim"));
		
		
		try {
			
			//create 2 Role, When Application run 1st time. if role is created before then it will not created again.
			Role role = new Role();
			role.setId(ApplicationConstants.ADMIN_USER_ID_HARDCODED_ID_VALUE);//role.setId(7001);
			role.setName("ADMIN_USER");
			
			Role role2 = new Role();
			role2.setId(ApplicationConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE);//role.setId(7001);
			role2.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role2);
			
			List<Role> rolesResult	 = this.roleRepository.saveAll(roles);
			
			//only systout for personal check.
			rolesResult.forEach(r->{
				System.out.println(r.getName());  
			});
			
			
		} catch (Exception e) {
			
		}
	}

}
