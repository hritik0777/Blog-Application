package com.hritik.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hritik.blog.entities.Category;
import com.hritik.blog.entities.Comment;
import com.hritik.blog.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	private String title;
	private String content;
	
	
    private String imageName;
	
	private Date addedDate;
	
	
	
	private CategoryDto category;
	
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	
	
	

}
