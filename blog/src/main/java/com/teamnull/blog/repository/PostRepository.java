package com.teamnull.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamnull.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreateAtDesc();
}
