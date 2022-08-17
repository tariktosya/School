package com.example.proje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.proje.model.entity.User;
import com.example.proje.model.response.user.UserListDto;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User findByUserId(Integer userId);

    @Query("Select new com.example.proje.model.response.user.UserListDto"
            + "(u.userId, u.email, u.firstName, u.lastName, u.phoneNumber)"
            + " From User u")
    List<UserListDto> findByUserListDto();

    @Query("Select new com.example.proje.model.response.user.UserListDto"
            + "(u.userId, u.email, u.firstName, u.lastName, u.phoneNumber)"
            + " From User u"
            + " WHERE u.email = ?1")
    UserListDto findByEmailToUserListDto(String email);
}