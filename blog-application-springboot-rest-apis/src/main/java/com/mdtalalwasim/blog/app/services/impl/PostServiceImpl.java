package com.mdtalalwasim.blog.app.services.impl;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.blog.app.entity.Category;
import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.entity.User;
import com.mdtalalwasim.blog.app.exceptions.ResourceNotFoundException;
import com.mdtalalwasim.blog.app.payloads.PostDto;
import com.mdtalalwasim.blog.app.payloads.PostResponse;
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

	private Page<Post> pageofpost;

	

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
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", "post id ", postId));
		
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage(postDto.getPostImage());
		Post updatedPost = this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		//this.postRepository.deleteById(postId); //or
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id ", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		 Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "post Id ", postId));
		 
		 return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

		Page<Post> pageOfPost = this.postRepository.findAll(pageable);
		// List<Post> findAllPost = this.postRepository.findAll();//old one-without
		// pagination
		List<Post> findAllPost = pageOfPost.getContent();

		List<PostDto> postDto = findAllPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pageOfPost.getNumber());
		postResponse.setPageSize(pageOfPost.getSize());
		postResponse.setTotalElemets(pageOfPost.getTotalElements());
		postResponse.setTotalPages(pageOfPost.getTotalPages());
		postResponse.setLastPage(pageOfPost.isLast());
		
		
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", "category id ", categoryId));
		List<Post> listOfPost = this.postRepository.findByCategory(cat);
		
		
		List<PostDto> postDto = listOfPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "user Id", userId));
		
		List<Post> listOfPostByUser = this.postRepository.findByUser(user);
		
		List<PostDto> postDto = listOfPostByUser.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDto> searchPostByKeyword(String keyword) {
		System.out.println(keyword+" this is keyword...");
		//List<Post> posts = this.postRepository.findByPostTitleContaining(keyword);
		List<Post> posts = this.postRepository.findByPostTitle(keyword);
		System.out.println(posts.toString());
		List<PostDto> postDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		System.out.println("Here ");
		return postDto;
	}

	@Override
	public List<PostDto> searchPostsTitleWithKewordContains(String keyword) {
		List<Post> posts = this.postRepository.findByPostTitleContaining(keyword);
		
		List<PostDto> postDto = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	

}
