package com.teamnull.blog.service;


import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.entity.enums.UserRoleEnum;
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
                                            User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(requestDto, user, post);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }


    // // 댓글 조회
    // @Transactional(readOnly = true)
    // public List<CommentResponseDto> getComment(Long postId){
    //     List<Comment> commentList = commentRepository.findAllBypost_IdByOrderByCreateAtDesc(postId);

    //     List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

    //     for(Comment comment : commentList){
    //         CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
    //         commentResponseDtoList.add(commentResponseDto);
    //     }

    //     return commentResponseDtoList;
    // }


    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long postId,
                                            Long commentId,
                                            CommentRequestDto requestDto,
                                            User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if (user.getRole() != UserRoleEnum.ADMIN) {
            if (!comment.getUser().getId().equals(user.getId()))
                throw new IllegalArgumentException("댓글 작성자만 수정이 가능합니다.");
        }

        if (user.getRole() == UserRoleEnum.ADMIN) {
            comment.updateComment(requestDto);
            commentRepository.save(comment);
        }

        comment.updateComment(requestDto);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }


    // 댓글 삭제
    public void deleteComment(Long postId, Long commentId, User user) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("조회하신 아이디의 게시글이 없습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("댓글이 존재하지 않습니다.")
        );

        if (user.getRole() != UserRoleEnum.ADMIN) {
            if (!comment.getUser().getId().equals(user.getId()))
                throw new IllegalArgumentException("댓글 작성자만 삭제 가능합니다.");
        }

        if (user.getRole() == UserRoleEnum.ADMIN) {
            commentRepository.delete(comment);
        }

        commentRepository.deleteById(commentId);


    }
}

