package com.example.proje.model.request.user;

import lombok.Data;

@Data
public class UserChangePasswordDto {

    private String email;

    private String password;
}
