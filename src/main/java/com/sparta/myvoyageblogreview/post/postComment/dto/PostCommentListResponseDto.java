package com.sparta.myvoyageblogreview.post.postComment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostCommentListResponseDto {
	private List<PostCommentResponseDto> postCommentList;

	public PostCommentListResponseDto(List<PostCommentResponseDto> postCommentList) {
		this.postCommentList = postCommentList;
	}
}
