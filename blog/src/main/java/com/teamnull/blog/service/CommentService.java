package com.teamnull.blog.service;


import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.repository.CommentRepository;
import com.teamnull.blog.repository.PostRepository;
import com.teamnull.blog.repository.UserRepository;
import com.teamnull.blog.util.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;



    // 댓글 등록
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto,
                                            HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );

            Comment comment = new Comment(requestDto.toEntity().getContents());
            commentRepository.save(comment);

            return new CommentResponseDto(comment);
        } else {
            return null;
        }
    }


    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComment(Long postId){
        List<Comment> commentList = commentRepository.findAllBypostIdByrderByCreateAtDesc(postId);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for(Comment comment : commentList){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }




    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId,
                                                  CommentRequestDto requestDto,
                                                  HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
            );

            comment.updateComment(requestDto);
            commentRepository.save(comment);

            return new CommentResponseDto(comment);

        } else {
            return null;
        }
    }


    // 댓글 삭제
    public String deleteComment(Long commentId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new NullPointerException("댓글이 존재하지 않습니다.")
            );

            commentRepository.deleteById(commentId);

            return "success";

        } else {
            return null;
        }
    }
}
