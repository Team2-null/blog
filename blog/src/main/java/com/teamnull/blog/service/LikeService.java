package com.teamnull.blog.service;

import com.teamnull.blog.dto.like.response.CommentLikeResponseDto;
import com.teamnull.blog.dto.like.response.PostLikeResponseDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeServiceInterface {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public CommentLikeResponseDto likeComment(Long commentId, User user) {
        boolean islike = false;

        Likes likeLog = likeRepository.findByTypeAndUser_IdAndTargetId(LikeEnum.COMMENT, user.getId(), commentId).orElse(null);

        String msg = "";

        if(likeLog == null) {
            likeLog = new Likes(user, LikeEnum.COMMENT, commentId);
            islike = true;
            likeRepository.save(likeLog);
            msg = "좋아요를 누르셨습니다.";
        } else {
            islike = false;
            likeRepository.delete(likeLog);
            msg = "좋아요를 취소하셨습니다.";
        }

        Comment comment = commentRepository.findById(commentId)
                            .orElseThrow(() -> new IllegalArgumentException("잘못된 접근 입니다."));

        comment.updateLike(islike);

        return new CommentLikeResponseDto(msg,200);
    }

    @Override
    @Transactional
    public PostLikeResponseDto likePost(Long postId, User user) {
        boolean islike = false;

        Likes likeLog = likeRepository.findByTypeAndUser_IdAndTargetId(LikeEnum.POST, user.getId(), postId).orElse(null);

        String msg = "";

        if(likeLog == null) {
            likeLog = new Likes(user, LikeEnum.POST, postId);
            islike = true;
            likeRepository.save(likeLog);
            msg = "좋아요를 누르셨습니다.";
        } else {
            islike = false;
            likeRepository.delete(likeLog);
            msg = "좋아요를 취소하셨습니다.";
        }

        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("잘못된 접근 입니다."));

        post.updateLike(islike);
        
        return new PostLikeResponseDto(msg,200);
    }

    
}
