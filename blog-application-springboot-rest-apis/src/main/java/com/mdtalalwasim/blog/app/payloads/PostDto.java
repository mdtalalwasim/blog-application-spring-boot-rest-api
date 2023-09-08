package com.mdtalalwasim.blog.app.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private int id;
	
	@NotBlank
	@Size(min = 5)
	private String postTitle;
	
	@NotBlank
	@Size(min = 10)
	private String postContent;
	
	private String postImage;
	
	private Date postCreatedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	
}
