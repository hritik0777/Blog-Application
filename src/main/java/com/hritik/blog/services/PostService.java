package com.hritik.blog.services;

import java.util.List;

import com.hritik.blog.entities.Post;
import com.hritik.blog.payloads.PostDto;
import com.hritik.blog.payloads.PostResponse;

public interface PostService {
	
	
	// Create Post
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	
	//Update Post
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	
	//Delete Post
	
	void deletePost(Integer postId);
	
	
	//Get PostById
	
	PostDto getPostById(Integer postId);
	
	//Get All Posts
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String direction);
	
	//Get Posts By Category
	
	List<PostDto> getAllPostOfCategory(Integer categoryId);
	
	//Get Posts By User
	
	List<PostDto> getAllPostByUser(Integer userId);
	
	//Search Posts 
	
	List<PostDto> searchPost(String keyword);
	
	
	
	

}
