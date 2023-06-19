package com.hritik.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hritik.blog.entities.Comment;
import com.hritik.blog.entities.Post;
import com.hritik.blog.exceptions.ResourceNotFoundException;
import com.hritik.blog.payloads.CommentDto;
import com.hritik.blog.repositories.CommentRepo;
import com.hritik.blog.repositories.PostRepo;
import com.hritik.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post_id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment saved = this.commentRepo.save(comment);
		
		return this.modelMapper.map(saved, CommentDto.class);
		
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment =  this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment_Id", commentId));
		
		this.commentRepo.delete(comment);
		
	}

}
