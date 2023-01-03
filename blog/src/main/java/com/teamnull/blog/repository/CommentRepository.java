package com.teamnull.blog.repository;

import com.teamnull.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // List<Comment> findAllBypost_IdByOrderByCreateAtDesc(Long postId);
}
