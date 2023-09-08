package com.mdtalalwasim.blog.app.payloads;

import java.util.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	
	private String postTitle;
	
	
	private String postContent;
	
	private String postImage;
	
	private Date postCreatedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
}
