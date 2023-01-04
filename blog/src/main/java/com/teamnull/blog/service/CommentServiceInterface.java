package com.teamnull.blog.service;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.User;


public interface CommentServiceInterface {
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user);
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user);
    public void deleteComment(Long postId, Long commentId, User user);
}
