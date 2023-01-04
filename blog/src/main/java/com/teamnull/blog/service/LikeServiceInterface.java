package com.teamnull.blog.service;

import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.entity.User;

public interface LikeServiceInterface {
    public CommentResponseDto likeComment(Long commentId, User user);
    public PostGetResponseDto likePost(Long postId, User user);
}
