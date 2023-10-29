package com.mdtalalwasim.blog.app;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplicationSpringbootRestApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		
	}

}
