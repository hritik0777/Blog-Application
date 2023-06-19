package com.hritik.blog.services.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;




import com.hritik.blog.entities.Category;
import com.hritik.blog.entities.Post;
import com.hritik.blog.entities.User;
import com.hritik.blog.exceptions.ResourceNotFoundException;
import com.hritik.blog.payloads.PostDto;
import com.hritik.blog.payloads.PostResponse;
import com.hritik.blog.repositories.CategoryRepo;
import com.hritik.blog.repositories.PostRepo;
import com.hritik.blog.repositories.UserRepo;
import com.hritik.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Autowired
	private UserRepo userRepo;
	
	

	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));;
		
		Post post = this.modelMapper.map(postDto, Post.class);
		
		
		post.setImageName("default.png");
		
		post.setAddedDate(new Date());
		
		post.setCategory(category);
		
		post.setUser(user);
		
		
		Post newPost = this.postRepo.save(post);
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
     Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
	
     
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		
		Post updatedPost = this.postRepo.save(post);
		
		PostDto updatedPostDto = this.modelMapper.map(updatedPost, PostDto.class);
		
		return updatedPostDto;
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		
		this.postRepo.delete(post);
		

	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		
		PostDto postDto =  this.modelMapper.map(post, PostDto.class);
		
		return postDto;
	}
	
	

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy , String direction) {
		
		Sort sort=null;
		
		if(direction.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}
		else
		{
			sort = Sort.by(sortBy).descending();
		}
		
//		Pageable p = PageRequest.of(pageNumber, pageSize, Sort);
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		
		
		
		Page<Post> pagePost =  this.postRepo.findAll(p);
		
	    List<Post>  posts =  pagePost.getContent();
				
		
		List<PostDto> postDtos =  posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse postResponse =new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		
		postResponse.setTotalElements(pagePost.getTotalElements());
		
		postResponse.setTotalPages(pagePost.getTotalPages());
		
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostOfCategory(Integer categoryId ) {
	
		
		Category cat =  this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		List<Post> posts=   this.postRepo.findPostByCategory(cat);
		
		List<PostDto>  postDtos =  posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		
		
		
		List<Post> posts= this.postRepo.findPostByUser(user);
		
		List<PostDto>  postDtos =  posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
		
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

}
