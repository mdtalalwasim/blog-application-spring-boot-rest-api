package com.mdtalalwasim.blog.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.blog.app.entity.Category;
import com.mdtalalwasim.blog.app.exceptions.ResourceNotFoundException;
import com.mdtalalwasim.blog.app.payloads.CategoryDto;
import com.mdtalalwasim.blog.app.repository.CategoryRepository;
import com.mdtalalwasim.blog.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		System.out.println("Inside Category Impl");
		//convert categoryDto to Category Object
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategoryInDB = this.categoryRepository.save(category);
	
		return this.modelMapper.map(savedCategoryInDB, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category existingCategory = this.categoryRepository.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		
		existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		existingCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCategory = this.categoryRepository.save(existingCategory);
		
		
		
		return this.modelMapper.map(updateCategory, CategoryDto.class);
		
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category ", " Category Id ", categoryId));
		
		this.categoryRepository.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		 List<CategoryDto> categoryDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		 return categoryDtos;
	}

}
