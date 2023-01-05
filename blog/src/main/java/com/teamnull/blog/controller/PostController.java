package com.teamnull.blog.controller;

import java.util.List;

import com.teamnull.blog.dto.post.response.PostDeleteResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.service.LikeService;
import com.teamnull.blog.service.PostService;
import com.teamnull.blog.util.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final LikeService likeService;

    // 게시글 작성하기
    @PostMapping("/posts")
    public PostGetResponseDto createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, 
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postCreateRequestDto, userDetails.getUser());
    }

    // 게시글 전체 조회하기
    @GetMapping("/posts")
    public List<PostGetResponseDto> getAllPost() {
        return postService.getAllPost();
    }

    // 게시글 선택 조회하기
    @GetMapping("/posts/{id}")
    public PostGetResponseDto getSelectPost(@PathVariable Long id) {
        return postService.getSelectPost(id);
    }

    // 게시글 수정하기
    @PutMapping("/posts/{id}")
    @ResponseBody
    public PostGetResponseDto updatePost(@PathVariable Long id,
                           @RequestBody PostUpdateRequestDto postUpdateRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(id, postUpdateRequestDto, userDetails.getUser());
    }

    // 게시글 삭제하기
    @DeleteMapping("/posts/{id}")
    public PostDeleteResponseDto deletePost(@PathVariable Long id,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getUser());
        return new PostDeleteResponseDto("게시글 삭제 완료",200);
    }

    
    @PostMapping("/posts/{postId}/like")
    @ResponseBody
    public PostGetResponseDto likeComment(@PathVariable Long postId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return likeService.likePost(postId, userDetails.getUser());
    }
}