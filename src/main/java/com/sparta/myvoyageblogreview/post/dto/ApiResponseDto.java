package com.sparta.myvoyageblogreview.post.dto;

import lombok.Getter;

@Getter
public class ApiResponseDto {
	private String message;
	private int statusCode;

	public ApiResponseDto(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}
}
