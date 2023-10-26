package com.mdtalalwasim.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdtalalwasim.blog.app.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
