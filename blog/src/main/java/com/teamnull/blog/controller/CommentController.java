package com.teamnull.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import org.springframework.web.bind.annotation.*;

import com.teamnull.blog.service.CommentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

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


    @GetMapping("posts/{postId}/comments")
    public List<CommentResponseDto> getComment(@PathVariable Long postId){
        return commentService.getComment(postId);
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
