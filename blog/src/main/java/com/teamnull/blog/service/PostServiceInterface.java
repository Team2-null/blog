package com.teamnull.blog.service;

import java.util.List;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostDeleteResponseDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.entity.User;

public interface PostServiceInterface {
    public PostGetResponseDto createPost(PostCreateRequestDto postCreateRequestDto, User user);
    public List<PostGetResponseDto> getAllPost();
    public PostGetResponseDto getSelectPost(Long id);
    public PostGetResponseDto updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto, User user);
    public PostDeleteResponseDto deletePost(Long id, User user);
}
