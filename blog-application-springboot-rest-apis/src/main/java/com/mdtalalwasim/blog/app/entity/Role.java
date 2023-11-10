package com.mdtalalwasim.blog.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role {
	
	@Id
	//dont need to autoincrement the id for role. because we have only 2 / 3 role for our project. so we have hard coded this.
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	private String name;
	
	

}
