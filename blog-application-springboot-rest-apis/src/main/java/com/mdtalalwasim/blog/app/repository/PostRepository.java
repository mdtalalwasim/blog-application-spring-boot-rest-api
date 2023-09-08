package com.mdtalalwasim.blog.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdtalalwasim.blog.app.entity.Category;
import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.entity.User;


public interface PostRepository extends JpaRepository<Post, Integer>{
	
	
	//custom finder method
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	

}
