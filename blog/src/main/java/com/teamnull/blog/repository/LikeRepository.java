package com.teamnull.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamnull.blog.entity.Likes;
import com.teamnull.blog.entity.enums.LikeEnum;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByTypeAndUser_IdAndTargetId(LikeEnum type, Long userId, Long targetId);
}
