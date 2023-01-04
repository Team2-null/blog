package com.teamnull.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.dto.post.response.PostGetResponseDto;
import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Likes;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.entity.User;
import com.teamnull.blog.entity.enums.LikeEnum;
import com.teamnull.blog.repository.CommentRepository;
import com.teamnull.blog.repository.LikeRepository;
import com.teamnull.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeServiceInterface {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    
    @Override
    @Transactional
    public CommentResponseDto likeComment(Long commentId, User user) {
        boolean islike = false;

        Likes likeLog = likeRepository.findByTypeAndUser_IdAndTargetId(LikeEnum.COMMENT, user.getId(), commentId).orElse(null);

        if(likeLog == null) {
            likeLog = new Likes(user, LikeEnum.COMMENT, commentId);
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

    @Override
    @Transactional
    public PostGetResponseDto likePost(Long postId, User user) {
        boolean islike = false;

        Likes likeLog = likeRepository.findByTypeAndUser_IdAndTargetId(LikeEnum.POST, user.getId(), postId).orElse(null);

        if(likeLog == null) {
            likeLog = new Likes(user, LikeEnum.POST, postId);
            islike = true;
            likeRepository.save(likeLog);
        } else {
            islike = false;
            likeRepository.delete(likeLog);
        }

        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("잘못된 접근 입니다."));

        post.updateLike(islike);
        
        return new PostGetResponseDto(post);
    }

    
}
