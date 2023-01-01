package com.teamnull.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 작성하기
    @PostMapping("/posts")
    public Post createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest request) {
        return postService.createPost(postCreateRequestDto, request);
    }

    // 게시글 전체 조회하기
    @GetMapping("/posts")
    public List<PostGetResponseDto> inquiryAllPost() {
        return postService.getAllPost();
    }

    // 게시글 선택 조회하기
    @GetMapping("/posts/{id}")
    public PostGetResponseDto inquirySelectPost(@PathVariable Long id) {
        return postService.getSelectPost(id);
    }

    // 게시글 수정하기
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto, HttpServletRequest request) {
        return postService.updatePost(id, postUpdateRequestDto, request);
    }

    // 게시글 삭제하기
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestParam String password, HttpServletRequest request) {
        return postService.deletePost(id, password, request);
    }
}