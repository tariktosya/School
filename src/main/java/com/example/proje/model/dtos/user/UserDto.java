package com.example.proje.model.dtos.user;

import lombok.Data;

@Data
public class UserDto {

    private Integer userId;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}