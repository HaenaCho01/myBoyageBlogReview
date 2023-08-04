package com.sparta.myvoyageblogreview.post.postComment.service;

import com.sparta.myvoyageblogreview.post.entity.Post;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentListResponseDto;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentRequestDto;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentResponseDto;
import com.sparta.myvoyageblogreview.post.postComment.entity.PostComment;
import com.sparta.myvoyageblogreview.post.postComment.repository.PostCommentRepository;
import com.sparta.myvoyageblogreview.post.service.PostService;
import com.sparta.myvoyageblogreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostCommentService {
	private final PostCommentRepository postCommentRepository;
	private final PostService postService;

	// 댓글 작성
	@Transactional
	public PostCommentResponseDto createComment(Long postId, PostCommentRequestDto requestDto, User user) {
		Post post = postService.findPost(postId);
		PostComment postComment = new PostComment(post, requestDto, user);
		return new PostCommentResponseDto(postCommentRepository.save(postComment));
	}

	// 게시글에 대한 전체 댓글 조회
	@Transactional(readOnly = true)
	public PostCommentListResponseDto getCommentsByPostId(Long postId) {
		Post post = postService.findPost(postId);
		List<PostCommentResponseDto> postCommentList = postCommentRepository.findAllByPostOrderByCreatedAtDesc(post).stream()
				.map(PostCommentResponseDto::new)
				.collect(Collectors.toList());
		return new PostCommentListResponseDto(postCommentList);
	}

	// 댓글 수정
	@Transactional
	public PostCommentResponseDto updateComment(Long postId, Long commentId, PostCommentRequestDto requestDto, User user) {
		PostComment comment = findComment(commentId);
		if (!postId.equals(comment.getPost().getId())) {
			throw new IllegalArgumentException("해당 페이지를 찾을 수 없습니다.");
		}
		if (!comment.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
		}
		comment.update(requestDto);
		return new PostCommentResponseDto(comment);
	}

	// 댓글 삭제
	public void deleteComment(Long postId, Long commentId, User user) {
		PostComment comment = findComment(commentId);
		if (!postId.equals(comment.getPost().getId())) {
			throw new IllegalArgumentException("해당 페이지를 찾을 수 없습니다.");
		}
		if (!comment.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
		}
		postCommentRepository.delete(comment);
	}

	// 게시글 id로 댓글 찾기
	public PostComment findComment(Long commentId) {
		return postCommentRepository.findById(commentId).orElseThrow(() ->
				new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
		);
	}
}
