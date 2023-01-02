package com.teamnull.blog.service;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;

public interface CommentServiceInterface {

    CommentResponseDto createComment(Long postId,
                                     CommentRequestDto requestDto,
                                     HttpServletRequest request);

    CommentResponseDto updateComment(Long commentId,
                                           CommentRequestDto requestDto,
                                           HttpServletRequest request);

    String deleteComment(Long commentId, HttpServletRequest request);

}
