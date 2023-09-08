package com.mdtalalwasim.blog.app.entity;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "posts")

@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "post_title", length = 100, nullable = false)
	private String postTitle;
	
	@Column(name = "post_content", length=2000)
	private String postContent;
	
	@Column(name = "post_image")
	private String postImage;
	
	@Column(name = "post_date")
	private Date postCreatedDate;
	
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
}
