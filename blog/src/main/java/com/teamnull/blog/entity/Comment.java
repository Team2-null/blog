package com.teamnull.blog.entity;

import javax.persistence.*;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Builder
@Entity
@Getter
@NoArgsConstructor
// @AllArgsConstructor
public class Comment extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // commentId
    private Long id;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column
    private Integer likes;

    public Comment(CommentRequestDto requestDto, User user, Post post) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.post = post;
        this.likes = 0;
    }


    public void updateComment(CommentRequestDto requestDto){
        this.contents = requestDto.getContents();
    }

    public void updateLike(boolean islike) {
        likes += islike ? 1 : -1;
        if(likes < 0) likes = 0;
    }
    
}
