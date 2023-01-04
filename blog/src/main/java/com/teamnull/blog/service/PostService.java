package com.teamnull.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.entity.enums.UserRoleEnum;
import org.springframework.stereotype.Service;

import com.teamnull.blog.entity.Post;
import com.teamnull.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 얘가 있어야 final이 붙어있는 레퍼지토리 필드에 연결
public class PostService implements PostServiceInterface {
    private final PostRepository postRepository;
    // 게시글 작성
    @Transactional
    public PostGetResponseDto createPost(PostCreateRequestDto postCreateRequestDto, User user) {
        Post post = new Post(postCreateRequestDto, user);
        postRepository.save(post);
        return new PostGetResponseDto(post);
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<PostGetResponseDto> getAllPost() {
        List<PostGetResponseDto> postList = postRepository.findAllByOrderByCreateAtDesc()
                                            .stream().map(PostGetResponseDto::new).collect(Collectors.toList());
                                             
        return postList;
    }

    // 게시글 선택 조회
    @Transactional(readOnly = true)
    public PostGetResponseDto getSelectPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        return new PostGetResponseDto(post);
    }

    // 게시글 수정하기
    @Transactional
    public PostGetResponseDto updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        
        if (user.getRole() != UserRoleEnum.ADMIN) {
            if (!post.getUser().getId().equals(user.getId()))
                throw new IllegalArgumentException("글 작성자만 수정이 가능합니다.");
        }

        post.updatePost(postUpdateRequestDto);
        return new PostGetResponseDto(post);
    }

    // 게시글 삭제하기
    @Transactional
    public void deletePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );

        if (user.getRole() == UserRoleEnum.ADMIN) {
            postRepository.delete(post);
        }

        post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("작성자만 삭제 가능합니다")
        );
        postRepository.deleteById(id);
    }
}