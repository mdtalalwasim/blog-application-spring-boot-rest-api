package com.mdtalalwasim.blog.app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	
	
	private int id;
	
	@NotBlank
	@Size(min = 4) 
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
}
