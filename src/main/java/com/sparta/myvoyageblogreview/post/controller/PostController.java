package com.sparta.myvoyageblogreview.post.controller;

import com.sparta.myvoyageblogreview.post.dto.PostRequestDto;
import com.sparta.myvoyageblogreview.post.dto.PostResponseDto;
import com.sparta.myvoyageblogreview.post.dto.PostsListResponseDto;
import com.sparta.myvoyageblogreview.post.service.PostService;
import com.sparta.myvoyageblogreview.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
	private final PostService postService;

	// 전체 게시글 목록 조회
	@GetMapping("/posts")
	public ResponseEntity<PostsListResponseDto> getPost() {
		return ResponseEntity.ok().body(postService.getPosts());
	}

	// 게시글 작성
	@PostMapping("/posts")
	public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		return ResponseEntity.ok().body(postService.createPost(requestDto, userDetails.getUser()));
	}
}
