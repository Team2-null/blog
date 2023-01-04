package com.teamnull.blog.dto.post.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.teamnull.blog.dto.comment.response.CommentResponseDto;
import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Post;

import lombok.Getter;

@Getter
public class PostGetResponseDto {
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponseDto> comments;
    private final Integer likeCount;

    public PostGetResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreateAt();
        this.modifiedAt = post.getModifiedAt();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.likeCount = post.getLikes();
    }
}