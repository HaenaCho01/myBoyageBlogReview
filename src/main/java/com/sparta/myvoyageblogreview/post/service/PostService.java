package com.sparta.myvoyageblogreview.post.service;

import com.sparta.myvoyageblogreview.post.dto.PostRequestDto;
import com.sparta.myvoyageblogreview.post.dto.PostResponseDto;
import com.sparta.myvoyageblogreview.post.dto.PostsListResponseDto;
import com.sparta.myvoyageblogreview.post.dto.PostsResponseDto;
import com.sparta.myvoyageblogreview.post.entity.Post;
import com.sparta.myvoyageblogreview.post.repository.PostRepository;
import com.sparta.myvoyageblogreview.user.entity.User;
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
	public PostsListResponseDto getPosts() {
		List<PostsResponseDto> postsList = postRepository.findAllByOrderByCreatedAtDesc().stream()
				.map(PostsResponseDto::new)
				.collect(Collectors.toList());
		return new PostsListResponseDto(postsList);
	}

	// 게시글 작성
	@Transactional
	public PostResponseDto createPost(PostRequestDto requestDto, User user) {
		Post post = new Post(requestDto, user);
		return new PostResponseDto(postRepository.save(post));
	}

	// 게시글 조회
	@Transactional(readOnly = true)
	public PostResponseDto getPostById(Long postId) {
		return new PostResponseDto(findPost(postId));
	}

	// 게시글 수정
	@Transactional
	public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
		Post post = findPost(postId);
		if (!post.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
		}
		post.update(requestDto);
		return new PostResponseDto(post);
	}

	// 게시글 삭제
	public void deletePost(Long postId, User user) {
		Post post = findPost(postId);
		if (!post.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
		}
		postRepository.delete(post);
	}

	// 게시글 id로 게시글 찾기
	public Post findPost(Long postId) {
		return postRepository.findById(postId).orElseThrow(() ->
				new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
		);
	}
}
