package com.sparta.myvoyageblogreview.post.entity;

import com.sparta.myvoyageblogreview.common.entity.Timestamped;
import com.sparta.myvoyageblogreview.post.dto.PostRequestDto;
import com.sparta.myvoyageblogreview.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@Column(name = "content", nullable = false)
	private String content;

	public Post(PostRequestDto requestDto, User user) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.user = user;
	}

	public void update(PostRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
	}
}
