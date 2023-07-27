package com.sparta.myvoyageblogreview.post.service;

import com.sparta.myvoyageblogreview.post.dto.PostsResponseDto;
import com.sparta.myvoyageblogreview.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	// 전체 게시글 목록 조회
	@Transactional(readOnly = true)
	public List<PostsResponseDto> getPosts() {
		List<PostsResponseDto> postList = postRepository.findAll().stream()
				.map(PostsResponseDto::new)
				.collect(Collectors.toList());
		return postList;
	}
}
