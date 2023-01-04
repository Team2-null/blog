package com.teamnull.blog.service;

import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.User;

public interface LikeServiceInterface {
    public CommentResponseDto likeComment(Long postId, Long commentId, User user);
}
