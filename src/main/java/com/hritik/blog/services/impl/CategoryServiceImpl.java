package com.hritik.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hritik.blog.entities.Category;
import com.hritik.blog.exceptions.ResourceNotFoundException;
import com.hritik.blog.payloads.CategoryDto;
import com.hritik.blog.repositories.CategoryRepo;
import com.hritik.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		
		Category category=  this.modelMapper.map(categoryDto, Category.class);
		
		
		this.categoryRepo.save(category);
		
		
		return this.modelMapper.map(category, CategoryDto.class);
		
		
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		
		Category category  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
				
	   category.setCategoryTitle(categoryDto.getCategoryTitle());
	 
	  	
	   	category.setCategoryDescription(categoryDto.getCategoryDescription());
	   	
	   	Category  updatedCategory = this.categoryRepo.save(category);
	   	
	   	
	   	
	   	
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto>  categoriesDtos =categories.stream().map((category)-> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		
		return categoriesDtos;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		
		Category category  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));

		this.categoryRepo.delete(category);
		
	}

}
