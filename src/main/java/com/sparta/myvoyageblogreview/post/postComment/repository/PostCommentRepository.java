package com.sparta.myvoyageblogreview.post.postComment.repository;

import com.sparta.myvoyageblogreview.post.entity.Post;
import com.sparta.myvoyageblogreview.post.postComment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
	List<PostComment> findAllByPostOrderByCreatedAtDesc(Post post);
}