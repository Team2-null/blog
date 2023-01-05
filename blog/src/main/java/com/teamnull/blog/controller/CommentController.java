package com.teamnull.blog.controller;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentDeleteResponseDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.dto.like.response.CommentLikeResponseDto;
import com.teamnull.blog.service.CommentService;
import com.teamnull.blog.service.LikeService;
import com.teamnull.blog.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("/{postId}/comments")
    @ResponseBody
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(postId, requestDto, userDetails.getUser());
    }

    @PutMapping("/{postId}/comments/{commentId}")
    @ResponseBody
    public CommentResponseDto updateComment(@PathVariable Long postId,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(postId, commentId, requestDto, userDetails.getUser());
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    @ResponseBody
    public CommentDeleteResponseDto deleteComment(@PathVariable Long postId,
                                                  @PathVariable Long commentId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){

        return commentService.deleteComment(postId, commentId, userDetails.getUser());
    }

    @PostMapping("/{postId}/comments/{commentId}/like")
    @ResponseBody
    public CommentLikeResponseDto likeComment(@PathVariable Long commentId,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return likeService.likeComment(commentId, userDetails.getUser());
    }
}
