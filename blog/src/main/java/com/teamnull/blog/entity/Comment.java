package com.teamnull.blog.entity;

import javax.persistence.*;

import com.teamnull.blog.dto.comment.request.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public Comment(String contents) {
        this.contents = contents;
    }


    public void updateComment(CommentRequestDto requestDto){
        this.contents = requestDto.toEntity().getContents();
    }

    // 토큰으로 사용자 검사해서 없어도 되지 않나요?
//    public boolean isMatchedUserId(Long userId) {
//        return this.user.getId().equals(userId);
//    }
    
}
