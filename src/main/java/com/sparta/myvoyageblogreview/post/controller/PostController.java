package com.sparta.myvoyageblogreview.post.controller;

import com.sparta.myvoyageblogreview.post.dto.PostsResponseDto;
import com.sparta.myvoyageblogreview.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
	private final PostService postService;

	// 전체 게시글 목록 조회
	@GetMapping("/posts")
	public ResponseEntity<List<PostsResponseDto>> getPost() {
		return ResponseEntity.ok().body(postService.getPosts());
	}
}
