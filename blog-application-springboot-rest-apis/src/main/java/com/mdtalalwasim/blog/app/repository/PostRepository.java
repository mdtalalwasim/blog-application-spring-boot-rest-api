package com.mdtalalwasim.blog.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mdtalalwasim.blog.app.entity.Category;
import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.entity.User;
import com.mdtalalwasim.blog.app.payloads.PostDto;


public interface PostRepository extends JpaRepository<Post, Integer>{
	
	
	//custom finder method
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitle(String keyword); // this will exact match with keyword
	
	List<Post> findByPostTitleContaining(String keyword);
	
	/*
	 * @Query("SELECT p FROM Post p WHERE p.postTitle = :title") List<Post>
	 * findByPostTitle(@Param("title") String title);
	 */
	
	//List<Post> findByPostContent(String title);
	

}
