package com.teamnull.blog.service;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;



public interface CommentServiceInterface {
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, HttpServletRequest request);
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, HttpServletRequest request);
    public String deleteComment(Long commentId, HttpServletRequest request);
}
