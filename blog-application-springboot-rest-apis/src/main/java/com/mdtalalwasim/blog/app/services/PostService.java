package com.mdtalalwasim.blog.app.services;

import java.util.List;

import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.payloads.PostDto;

public interface PostService {
	
	//CRUD methods
	
	//create post
	Post createPost(PostDto postDto);
	
	//update post
	Post updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get Single post
	Post getPost(Integer postId);
	
	//get list of post
	List<Post> getAllPost();
	
	//get post by category
	
	List<Post> getAllPostByCategory(Integer categoryId);
	
	//get all post by user
	List<Post> getAllPostByUser(Integer userId);
	
	//searchPost
	List<Post> searchPostByKeyword(String keyword);
	
	

}
