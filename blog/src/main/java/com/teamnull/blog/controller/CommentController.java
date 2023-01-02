package com.teamnull.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamnull.blog.dto.comment.request.CommentCreateRequestDto;
import com.teamnull.blog.dto.comment.request.CommentDeleteRequestDto;
import com.teamnull.blog.dto.comment.request.CommentUpdateRequestDto;
import com.teamnull.blog.dto.comment.response.CommentCreateResponseDto;
import com.teamnull.blog.dto.comment.response.CommentDeleteResponseDto;
import com.teamnull.blog.dto.comment.response.CommentUpdateResponseDto;
import com.teamnull.blog.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    @ResponseBody
    public CommentCreateResponseDto createComment(CommentCreateRequestDto requestDto, HttpServletRequest request){
        return commentService.createComment(requestDto, request);
    }

    @PutMapping("/comments/{commentId}")
    @ResponseBody
    public CommentUpdateResponseDto updateComment(@PathVariable Long commentId, CommentUpdateRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(commentId, requestDto, request);
    }

    @DeleteMapping("/comment/{commentId}")
    @ResponseBody
    public CommentDeleteResponseDto deleteComment(@PathVariable Long commentId, CommentDeleteRequestDto requestDto, HttpServletRequest request){
        return commentService.deleteComment(commentId, requestDto, request);
    }

}
