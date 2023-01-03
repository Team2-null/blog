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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post(PostCreateRequestDto postCreateRequestDto, User user) {
        this.title = postCreateRequestDto.getTitle();
        this.writer = postCreateRequestDto.getWriter();
        this.content = postCreateRequestDto.getContent();
        this.password = postCreateRequestDto.getPassword();
        this.user = getUser();
    }
    
    public boolean isValidPassword(String inputPassword) {
        if (inputPassword.equals(this.password)) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.writer = postUpdateRequestDto.getWriter();
        this.content = postUpdateRequestDto.getContent();
    }
}