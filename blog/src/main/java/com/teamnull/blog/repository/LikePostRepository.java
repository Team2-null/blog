package com.teamnull.blog.repository;

import com.teamnull.blog.entity.Likes;
import com.teamnull.blog.entity.enums.LikeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikePostRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByTypeAndUser_IdAndTargetId(LikeEnum type, Long userId, Long targetId);
}
