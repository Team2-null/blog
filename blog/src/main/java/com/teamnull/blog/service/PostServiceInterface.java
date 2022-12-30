package com.teamnull.blog.service;

import java.util.List;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.entity.Post;

public interface PostServiceInterface {
    public Post createPost(PostCreateRequestDto postCreateRequestDto);
    public List<PostGetResponseDto> getAllPost();
    public PostGetResponseDto getSelectPost(Long id);
    public Post updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto);
    public String deletePost(Long id, String password);
}
