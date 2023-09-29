package com.mdtalalwasim.blog.app.services;

import java.util.List;

import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.payloads.PostDto;

public interface PostService {
	
	//CRUD methods
	
	//create post
	PostDto createPost(PostDto postDto, Integer userId, Integer postId);
	
	//update post
	Post updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get Single post
	PostDto getPostById(Integer postId);
	
	//get list of post
	List<PostDto> getAllPost();
	
	//get post by category
	
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getAllPostByUser(Integer userId);
	
	//searchPost
	List<Post> searchPostByKeyword(String keyword);
	
	

}
