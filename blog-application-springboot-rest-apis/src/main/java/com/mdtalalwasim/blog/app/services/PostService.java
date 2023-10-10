package com.mdtalalwasim.blog.app.services;

import java.util.List;

import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.payloads.PostDto;
import com.mdtalalwasim.blog.app.payloads.PostResponse;

public interface PostService {
	
	//CRUD methods
	
	//create post
	PostDto createPost(PostDto postDto, Integer userId, Integer postId);
	
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get Single post
	PostDto getPostById(Integer postId);
	
	//get list of post
	//List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	//get post by category
	
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getAllPostByUser(Integer userId);
	
	//searchPost
	List<PostDto> searchPostByKeyword(String keyword);
	
	List<PostDto> searchPostsTitleWithKewordContains(String keyword);
	
	

}
