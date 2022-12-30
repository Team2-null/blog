package com.teamnull.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamnull.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
