package com.mdtalalwasim.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdtalalwasim.blog.app.payloads.PostDto;
import com.mdtalalwasim.blog.app.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostRestController {

	@Autowired
	PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") Integer userId) {

		List<PostDto> allPostByUser = this.postService.getAllPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(allPostByUser, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {

		List<PostDto> allPostByCategory = this.postService.getAllPostByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(allPostByCategory, HttpStatus.OK);
	}

	// get all post 
	@GetMapping("/all-post")
	public ResponseEntity<List<PostDto>> getAllPost() {

		List<PostDto> allPost = this.postService.getAllPost();

		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);

	}

	// get single post by Id
	@GetMapping("/single-post/{postId}")
	public ResponseEntity<PostDto> getSinglePostById(@PathVariable Integer postId) {

		PostDto singlePost = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(singlePost, HttpStatus.OK);

	}

}
