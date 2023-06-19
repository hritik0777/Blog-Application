package com.hritik.blog.services;

import java.util.List;

import com.hritik.blog.payloads.CategoryDto;

public interface CategoryService {
	
	
	
	
	// createCategory
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// updateCategory 
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	

	
	// getCategoryById
	
	CategoryDto getCategory(Integer categoryId);
	
	// gellAllCategory
	
	
	List<CategoryDto> getAllCategory();
	
	//deleteCategory
	
	void deleteCategory(Integer categoryId);

}
