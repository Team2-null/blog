package com.teamnull.blog.service;

import com.teamnull.blog.dto.like.response.CommentLikeResponseDto;
import com.teamnull.blog.dto.like.response.PostLikeResponseDto;
import com.teamnull.blog.entity.User;

public interface LikeServiceInterface {
    public CommentLikeResponseDto likeComment(Long commentId, User user);
    public PostLikeResponseDto likePost(Long postId, User user);
}
