package com.teamnull.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamnull.blog.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("posts/{postId}/comments")
    @ResponseBody
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            CommentRequestDto requestDto,
                                            HttpServletRequest request){
        return commentService.createComment(postId, requestDto, request);
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    @ResponseBody
    public CommentResponseDto updateComment(@PathVariable Long commentId, CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(commentId, requestDto, request);
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable Long commentId, HttpServletRequest request){
        return "success";
    }

}
