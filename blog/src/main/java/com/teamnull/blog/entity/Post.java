package com.teamnull.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.teamnull.blog.dto.post.request.PostCreateRequestDto;
import com.teamnull.blog.dto.post.request.PostUpdateRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    @OrderBy("modifiedAt desc")
    private final List<Comment> comments = new ArrayList<>();

    public Post(PostCreateRequestDto postCreateRequestDto, User user) {
        this.title = postCreateRequestDto.getTitle();
        this.content = postCreateRequestDto.getContent();
        this.user = user;
        this.likes = 0;
    }

    public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
    }

    public void updateLike(boolean islike) {
        likes += islike ? 1 : -1;
        if(likes < 0) likes = 0;
    }
}