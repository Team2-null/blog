package com.teamnull.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.CommentLike;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.repository.CommentRepository;
import com.teamnull.blog.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeServiceInterface {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    
    @Override
    @Transactional
    public CommentResponseDto likeComment(Long postId, Long commentId, User user) {
        boolean islike = false;
        CommentLike likeLog = likeRepository.findByUser_IdAndCommentId(user.getId(), commentId).orElse(null);


        if(likeLog == null) {
            likeLog = new CommentLike(user, postId, commentId);
            islike = true;
            likeRepository.save(likeLog);
        } else {
            islike = false;
            likeRepository.delete(likeLog);
        }

        Comment comment = commentRepository.findById(commentId)
                            .orElseThrow(() -> new IllegalArgumentException("잘못된 접근 입니다."));

        comment.updateLike(islike);
        
        return new CommentResponseDto(comment);
    }
}
