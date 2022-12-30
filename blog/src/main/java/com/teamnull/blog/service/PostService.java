package com.teamnull.blog.service;

import java.util.ArrayList;
import java.util.List;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import org.springframework.stereotype.Service;

import com.teamnull.blog.entity.Post;
import com.teamnull.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 얘가 있어야 final이 붙어있는 레퍼지토리 필드에 연결
public class PostService implements PostServiceInterface{
    private final PostRepository postRepository;

    // 게시글 작성
    public Post createPost(PostCreateRequestDto postCreateRequestDto) {
        Post post = new Post(postCreateRequestDto);
        postRepository.save(post);
        return post;
    }

    // 게시글 전체 조회
    public List<PostGetResponseDto> getAllPost() {
        List<Post> postList = postRepository.findAllByOrderByCreateAtDesc();
        List<PostGetResponseDto> postGetResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postGetResponseDtoList.add(new PostGetResponseDto(post));
        }
        return postGetResponseDtoList;
    }

    // 게시글 선택 조회
    public PostGetResponseDto getSelectPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        return new PostGetResponseDto(post);
    }

    // 게시글 수정하기
    public Post updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        if (post.isValidPassword(postUpdateRequestDto.getPassword())) {
            post.updatePost(postUpdateRequestDto);
            postRepository.save(post);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return post;
    }

    // 게시글 삭제하기
    public String deletePost(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        if (post.isValidPassword(password)) {
            postRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return "삭제 완료";
    }
}