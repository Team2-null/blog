package com.teamnull.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamnull.blog.entity.CommentLike;

public interface LikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUser_IdAndCommentId(Long userId, Long commentId);
}
