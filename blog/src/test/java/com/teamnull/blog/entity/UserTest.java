package com.teamnull.blog.entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.teamnull.blog.entity.enums.UserRoleEnum;

public class UserTest {
    @Nested
    @DisplayName("회원 가입")
    class CreateUser {
        private Long id;
        private String username;
        private String password;
        private UserRoleEnum role;
    
        @BeforeEach
        void setup() {
            id = 1L;
            username = "user1";
            password = "1q2w3e4r!";
            role = UserRoleEnum.USER;
        }

        @Test
        @DisplayName("정상 케이스")
        void createUser_Normal() {
            //when
            User user = new User(username, password, role);

            //then
            assertNull(user.getId());
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
            assertEquals(role, user.getRole());
        }

        @Test
        @DisplayName("Username Null case")
        void createUser_Null_username() {
            //given 
            username = null;
            //when
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new User(username, password, role);
            });
            //then
            assertEquals("저장할 수 있는 사용자명이 없습니다.", exception.getMessage());
        }

        @Test
        @DisplayName("Password Null case")
        void createUser_Null_password() {
            //given 
            password = null;
            //when
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new User(username, password, role);
            });
            //then
            assertEquals("저장할 수 있는 패스워드가 없습니다.", exception.getMessage());
        }

        @Test
        @DisplayName("UserRole Null case")
        void createUser_Null_UserRole() {
            //given 
            role = null;
            //when
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new User(username, password, role);
            });
            //then
            assertEquals("저장할 수 있는 역할이 없습니다.", exception.getMessage());
        }
    }
    
}
