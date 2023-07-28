package com.sparta.myvoyageblogreview.post.dto;

import com.sparta.myvoyageblogreview.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
	private String title;
	private String username;
	private LocalDateTime createdAt;
	private String content;

	public PostResponseDto(Post post) {
		this.title = post.getTitle();
		this.username = post.getUser().getUsername();
		this.createdAt = post.getCreatedAt();
		this.content = post.getContent();
	}
}
