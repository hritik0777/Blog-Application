package com.hritik.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hritik.blog.payloads.ApiResponse;
import com.hritik.blog.payloads.CommentDto;
import com.hritik.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	// Create Comment 
	
	
	@PostMapping("/comment/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		
		CommentDto createdComment = this.commentService.createComment(commentDto, postId);
		
		
		return new ResponseEntity<CommentDto>(createdComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/delete/{comment_id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer comment_id){
		
		this.commentService.deleteComment(comment_id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted comment", true),HttpStatus.OK);
	}

}
