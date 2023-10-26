package com.mdtalalwasim.blog.app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdtalalwasim.blog.app.entity.Comment;
import com.mdtalalwasim.blog.app.entity.Post;
import com.mdtalalwasim.blog.app.exceptions.ResourceNotFoundException;
import com.mdtalalwasim.blog.app.payloads.CommentDto;
import com.mdtalalwasim.blog.app.repository.CommentRepository;
import com.mdtalalwasim.blog.app.repository.PostRepository;
import com.mdtalalwasim.blog.app.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post ", "post id", postId) );
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepository.save(comment);
		
		
		
		
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment ", "comment id ", commentId));
		
		this.commentRepository.delete(comment);
		

	}

}
