package com.sparta.myvoyageblogreview.post.postComment.controller;

import com.sparta.myvoyageblogreview.post.dto.ApiResponseDto;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentListResponseDto;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentRequestDto;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentResponseDto;
import com.sparta.myvoyageblogreview.post.postComment.service.PostCommentService;
import com.sparta.myvoyageblogreview.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostCommentController {
	private final PostCommentService postCommentService;

	// 댓글 작성
	@PostMapping("/{postId}/comments")
	public ResponseEntity<PostCommentResponseDto> createComment(@PathVariable Long postId, @RequestBody PostCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		return ResponseEntity.ok().body(postCommentService.createComment(postId, requestDto, userDetails.getUser()));
	}

	// 게시글에 대한 모든 댓글 조회
	@GetMapping("/{postId}/comments")
	public ResponseEntity<PostCommentListResponseDto> getCommentsByPostId(@PathVariable Long postId) {
		return ResponseEntity.ok().body(postCommentService.getCommentsByPostId(postId));
	}

	// 댓글 수정
	@PutMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<PostCommentResponseDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody PostCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		return ResponseEntity.ok().body(postCommentService.updateComment(postId, commentId, requestDto, userDetails.getUser()));
	}

	// 댓글 삭제
	@DeleteMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long postId,  @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		postCommentService.deleteComment(postId, commentId, userDetails.getUser());
		return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제를 완료하였습니다.", HttpStatus.OK.value()));
	}
}
