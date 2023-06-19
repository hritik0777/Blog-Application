package com.hritik.blog.repositories;
import java.util.*;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hritik.blog.entities.Category;
import com.hritik.blog.entities.Post;
import com.hritik.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
       List<Post> findPostByUser(User user);
       
       List<Post> findPostByCategory(Category category);
       
       List<Post> findByTitleContaining(String keyword);

} 

