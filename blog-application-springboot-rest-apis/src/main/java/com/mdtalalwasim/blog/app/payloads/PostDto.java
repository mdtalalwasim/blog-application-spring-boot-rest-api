package com.mdtalalwasim.blog.app.payloads;

import java.util.Date;

import com.mdtalalwasim.blog.app.entity.Category;
import com.mdtalalwasim.blog.app.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PostDto {
	
	
	private String postTitle;
	
	
	private String postContent;
	
	
}
