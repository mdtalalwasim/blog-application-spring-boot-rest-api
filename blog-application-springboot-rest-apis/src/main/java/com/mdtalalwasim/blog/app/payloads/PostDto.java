package com.mdtalalwasim.blog.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mdtalalwasim.blog.app.entity.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
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
	
	private Set<CommentDto> comments = new HashSet<>();
	
	
}
