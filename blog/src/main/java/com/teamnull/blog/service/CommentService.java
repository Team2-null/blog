package com.teamnull.blog.service;


import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.entity.enums.UserRoleEnum;
import com.teamnull.blog.repository.CommentRepository;
import com.teamnull.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 등록
    @Override
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto,
                                            User user) {
        Post post = findPost(postId);    
        Comment comment = new Comment(requestDto, user, post);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Override
    @Transactional
    public CommentResponseDto updateComment(Long postId,
                                            Long commentId,
                                            CommentRequestDto requestDto,
                                            User user) {
        Post post = findPost(postId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if (user.getRole() != UserRoleEnum.ADMIN) {
            if (!comment.getUser().getId().equals(user.getId()))
                throw new IllegalArgumentException("댓글 작성자만 수정이 가능합니다.");
        }


        comment.updateComment(requestDto);
        //commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }


    // 댓글 삭제
    public void deleteComment(Long postId, Long commentId, User user) {

        Post post = findPost(postId);

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

    // 
    private Post findPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return post;
    }
}

