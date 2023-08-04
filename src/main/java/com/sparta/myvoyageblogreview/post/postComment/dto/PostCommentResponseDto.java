package com.sparta.myvoyageblogreview.post.postComment.dto;

import com.sparta.myvoyageblogreview.post.postComment.entity.PostComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCommentResponseDto {
	private String content;
	private String username;
	private LocalDateTime createdAt;

	public PostCommentResponseDto (PostComment postComment) {
		this.content = postComment.getContent();
		this.username = postComment.getUser().getUsername();
		this.createdAt = postComment.getCreatedAt();
	}
}
