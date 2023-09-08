package com.mdtalalwasim.blog.app.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.blog.app.entity.Category;
import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.entity.User;
import com.mdtalalwasim.blog.app.exceptions.ResourceNotFoundException;
import com.mdtalalwasim.blog.app.payloads.PostDto;
import com.mdtalalwasim.blog.app.repository.CategoryRepository;
import com.mdtalalwasim.blog.app.repository.PostRepository;
import com.mdtalalwasim.blog.app.repository.UserRepository;
import com.mdtalalwasim.blog.app.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " Id ", userId));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "id ", categoryId));
		
		
		Post post = this.modelMapper.map(postDto, Post.class);//only come post title, and description other attribute we should set manually set
		
		post.setPostImage("default.png");//default image
		post.setPostCreatedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = this.postRepository.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
		
		
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		

	}

	@Override
	public Post getPost(Integer postId) {
		
		return null;
	}

	@Override
	public List<Post> getAllPost() {
		
		return null;
	}

	@Override
	public List<Post> getAllPostByCategory(Integer categoryId) {
		
		return null;
	}

	@Override
	public List<Post> getAllPostByUser(Integer userId) {
		
		return null;
	}

	@Override
	public List<Post> searchPostByKeyword(String keyword) {
		
		return null;
	}

}
