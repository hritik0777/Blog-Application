package com.hritik.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hritik.blog.payloads.ApiResponse;
import com.hritik.blog.payloads.CategoryDto;
import com.hritik.blog.payloads.UserDto;
import com.hritik.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	// Create Category 
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		
	CategoryDto createdCategory = 	  this.categoryService.createCategory(categoryDto);
		
	return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	
	
	}
	
	
	
	
	// Update Category 
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> UpdateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer catId)
	{
		
	CategoryDto updatedCategory = 	  this.categoryService.updateCategory(categoryDto,catId);
		
	return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	
	
	}
	
	
	// Delete Category 
	 
	 @DeleteMapping("/{catId}")
     public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
    	 
    	 this.categoryService.deleteCategory(catId);
    	 
    	 return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted", true),HttpStatus.OK);
    	 
     }
	 
	 
	 // Get Category By Id 
	 
	 @GetMapping("/{catId}")
     public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
     {
		 
		 CategoryDto categoryDto = categoryService.getCategory(catId);
		 
    	 return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
     }
	 
	 // Get All Categories 
	 
	 @GetMapping("/")
	 public ResponseEntity<List<CategoryDto>> getAllCategories()
	 {
		 List<CategoryDto>  categories = categoryService.getAllCategory();
		 
		 return new ResponseEntity<List<CategoryDto>>(categories , HttpStatus.OK);
	 }
	 
	 
	 
	

}
