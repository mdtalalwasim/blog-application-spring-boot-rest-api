package com.mdtalalwasim.blog.app.services;

import java.util.List;

import com.mdtalalwasim.blog.app.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//read/get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getAllCategory();
}
