package com.teamnull.blog.service;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentCreateRequestDto;
import com.teamnull.blog.dto.comment.request.CommentDeleteRequestDto;
import com.teamnull.blog.dto.comment.request.CommentUpdateRequestDto;
import com.teamnull.blog.dto.comment.response.CommentCreateResponseDto;
import com.teamnull.blog.dto.comment.response.CommentDeleteResponseDto;
import com.teamnull.blog.dto.comment.response.CommentUpdateResponseDto;

public interface CommentServiceInterface {
    public CommentCreateResponseDto createComment(CommentCreateRequestDto requestDto, HttpServletRequest request);
    public CommentUpdateResponseDto updateComment(CommentUpdateRequestDto requestDto, HttpServletRequest request);
    public CommentDeleteResponseDto deleteComment(CommentDeleteRequestDto requestDto, HttpServletRequest request);
}
