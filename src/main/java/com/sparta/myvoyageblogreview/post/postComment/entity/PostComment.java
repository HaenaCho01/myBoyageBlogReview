package com.sparta.myvoyageblogreview.post.postComment.entity;

import com.sparta.myvoyageblogreview.common.entity.Timestamped;
import com.sparta.myvoyageblogreview.post.entity.Post;
import com.sparta.myvoyageblogreview.post.postComment.dto.PostCommentRequestDto;
import com.sparta.myvoyageblogreview.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post_comments")
@NoArgsConstructor
public class PostComment extends Timestamped {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", nullable = false)
	private Post post;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "content", nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	public PostComment(Post post, PostCommentRequestDto requestDto, User user) {
		this.post = post;
		this.content = requestDto.getContent();
		this.user = user;
	}

	public void update(PostCommentRequestDto requestDto) {
		this.content = requestDto.getContent();
	}
}
