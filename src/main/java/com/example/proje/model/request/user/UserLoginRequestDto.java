package com.example.proje.model.request.user;

import lombok.Data;

@Data
public class UserLoginRequestDto {

    private String email;

    private String password;
}
