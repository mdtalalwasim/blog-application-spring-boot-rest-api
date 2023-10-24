package com.mdtalalwasim.blog.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.print.attribute.standard.Media;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mdtalalwasim.blog.app.config.ApplicationConstants;
import com.mdtalalwasim.blog.app.payloads.ApiResponse;
import com.mdtalalwasim.blog.app.payloads.PostDto;
import com.mdtalalwasim.blog.app.payloads.PostResponse;
import com.mdtalalwasim.blog.app.services.FileService;
import com.mdtalalwasim.blog.app.services.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostRestController {

	@Autowired
	PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	private PostDto postById;

	private PostDto  updatePost;
	

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
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = ApplicationConstants.PAGE_NUMBER, required = false) Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.PAGE_SIZE, required = false) Integer pageSize, 
			@RequestParam(value = "sortBy", defaultValue = ApplicationConstants.SORT_BY, required = false) String sortBy) {

		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	// get single post by Id
	@GetMapping("/single-post/{postId}")
	public ResponseEntity<PostDto> getSinglePostById(@PathVariable Integer postId) {

		PostDto singlePost = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(singlePost, HttpStatus.OK);

	}

	// delete post
	// use custom response class
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {

		this.postService.deletePost(postId);
		return new ApiResponse("Post id deleted successfully with this id:" + postId, true);
	}

	// update post
	// use custom response class
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	
	//serch post by title
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> post = this.postService.searchPostByKeyword(keywords);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search-contains/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitleContainsInPost(@PathVariable("keywords") String keywords){
		List<PostDto> post = this.postService.searchPostsTitleWithKewordContains(keywords);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId ) throws IOException{
		
		//upload image
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		
		postDto.setPostImage(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
		
	}
	
	//method to serve file
	@GetMapping(value ="/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
	
	
	

}
