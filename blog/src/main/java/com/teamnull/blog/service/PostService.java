package com.teamnull.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.dto.post.response.ResponseDto;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.entity.enums.UserRoleEnum;
import com.teamnull.blog.repository.CommentRepository;
import com.teamnull.blog.repository.UserRepository;
import com.teamnull.blog.util.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import com.teamnull.blog.entity.Post;
import com.teamnull.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 얘가 있어야 final이 붙어있는 레퍼지토리 필드에 연결
public class PostService implements PostServiceInterface {
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    // 게시글 작성
    public Post createPost(PostCreateRequestDto postCreateRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다");
            }
        }
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        Post post = new Post(postCreateRequestDto, user);
        postRepository.save(post);
        return post;
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<Post> getAllPost() {
        List<Post> postList = postRepository.findAllByOrderByCreateAtDesc();
        return postList;
    }

    // 게시글 선택 조회
    public PostGetResponseDto getSelectPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        return new PostGetResponseDto(post);
    }

    // 게시글 수정하기
    public Post updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto, HttpServletRequest request) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }
        }
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        if (user.getRole() == UserRoleEnum.ADMIN) {
            post.updatePost(postUpdateRequestDto);
            return post;
        }
        post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("작성자만 수정이 가능합니다.")
        );
        post.updatePost(postUpdateRequestDto);
        return post;


    }

    // 게시글 삭제하기
    public ResponseDto deletePost(Long id, HttpServletRequest request) throws ResponseDto {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResponseDto("조회하신 아이디의 게시글이 없습니다.")
        );
        ResponseDto response = new ResponseDto("게시물삭제가 완료되었습니다.");
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new ResponseDto("토큰이 유효하지 않습니다");
            }
        }
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new ResponseDto("회원을 찾을 수 없습니다")
        );
        if (user.getRole() == UserRoleEnum.ADMIN) {
            postRepository.delete(post);
            return response;
        }
        post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new ResponseDto("작성자만 삭제 가능합니다")
        );
        postRepository.deleteById(id);
        return response;
    }
}