package com.sparta.myvoyageblogreview.post.dto;

import com.sparta.myvoyageblogreview.post.entity.Post;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
public class PostResponseDto {
	private String title;
	private String username;
	private LocalDateTime createdAt;
	private String content;
	private List<PostCommentResponseDto> comments;

	public PostResponseDto(Post post) {
		this.title = post.getTitle();
		this.username = post.getUser().getUsername();
		this.createdAt = post.getCreatedAt();
		this.content = post.getContent();
		if (!(post.getComments() == null)) {
			this.comments = post.getComments().stream()
					.map(PostCommentResponseDto::new)
					.sorted(Comparator.comparing(PostCommentResponseDto::getCreatedAt).reversed())
					.toList();
		}
	}
}
