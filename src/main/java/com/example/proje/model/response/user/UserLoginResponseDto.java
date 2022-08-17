package com.example.proje.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {

    private Integer userId;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String jwt;
}
