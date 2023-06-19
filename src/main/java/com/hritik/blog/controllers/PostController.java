package com.hritik.blog.controllers;
import java.util.*;


import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hritik.blog.config.AppConstants;
import com.hritik.blog.entities.Post;
import com.hritik.blog.payloads.ApiResponse;
import com.hritik.blog.payloads.PostDto;
import com.hritik.blog.payloads.PostResponse;
import com.hritik.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// Create Post
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,@PathVariable Integer userId ,@PathVariable Integer categoryId)
	{
		
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		
		
		
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
	
	
	
	// Get Posts by User
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts= this.postService.getAllPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//Get Posts By Category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> posts=this.postService.getAllPostOfCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	// Get All Posts
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber" ,  defaultValue = AppConstants.PAGE_NUMBER , required = false) Integer pageNumber,
			@RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy" , defaultValue = AppConstants.SORTBY , required = false ) String sortBy,
			@RequestParam(value="direction" , defaultValue=AppConstants.DIRECTION , required = false ) String direction)
	{
		
		PostResponse posts = this.postService.getAllPost( pageNumber , pageSize , sortBy,direction);
		
		
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	
	
	// Get Post By PostId
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto post = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	
	// Delete a Post By PostId
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted",true),HttpStatus.OK);
	}
	
	
	
	// Update Post by PostId
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId)
	{
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
			
	}
	
	
	// Search Posts by Title
	
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords)
	{
		List<PostDto>  result =  this.postService.searchPost(keywords);
		
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	
	
	
}
