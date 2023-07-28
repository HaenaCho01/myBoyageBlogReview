package com.sparta.myvoyageblogreview.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostsListResponseDto {
	private List<PostsResponseDto> postsList;

	public PostsListResponseDto(List<PostsResponseDto> postsList) {
		this.postsList = postsList;
	}
}
