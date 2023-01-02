package com.teamnull.blog.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.teamnull.blog.dto.comment.request.CommentCreateRequestDto;
import com.teamnull.blog.dto.comment.request.CommentDeleteRequestDto;
import com.teamnull.blog.dto.comment.request.CommentUpdateRequestDto;
import com.teamnull.blog.dto.comment.response.CommentCreateResponseDto;
import com.teamnull.blog.dto.comment.response.CommentDeleteResponseDto;
import com.teamnull.blog.dto.comment.response.CommentUpdateResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {
    @Override
    public CommentCreateResponseDto createComment(CommentCreateRequestDto requestDto, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto,
            HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CommentDeleteResponseDto deleteComment(Long commentId, CommentDeleteRequestDto requestDto,
            HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
}
