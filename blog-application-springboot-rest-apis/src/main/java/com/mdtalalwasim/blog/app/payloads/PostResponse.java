package com.mdtalalwasim.blog.app.payloads;

import java.util.List;

import com.mdtalalwasim.blog.app.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

		private List<PostDto> content;
		private int pageNumber;
		private int pageSize;
		private long totalElemets;
		private int totalPages;
		private boolean isLastPage;
}
