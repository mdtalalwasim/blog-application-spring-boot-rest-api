package com.mdtalalwasim.blog.app.services;

import com.mdtalalwasim.blog.app.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);

}
