package com.teamnull.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.util.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.teamnull.blog.service.CommentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/posts/{postId}")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/comments")
    @ResponseBody
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(postId, requestDto, userDetails.getUser());
    }


    // @GetMapping("posts/{postId}/comments")
    // public List<CommentResponseDto> getComment(@PathVariable Long postId){
    //     return commentService.getComment(postId);
    // }


    @PutMapping("/comments/{commentId}")
    @ResponseBody
    public CommentResponseDto updateComment(@PathVariable Long postId,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(postId, commentId, requestDto, userDetails.getUser());
    }


    @DeleteMapping("/comments/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(postId, commentId, userDetails.getUser());
        return "success";
    }

}
